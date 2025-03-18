package com.example.weightcontrolapp;

import com.example.weightcontrolapp.constants.ErrorCodes;
import com.example.weightcontrolapp.dto.request.MealRequest;
import com.example.weightcontrolapp.dto.response.MealResponse;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import com.example.weightcontrolapp.entity.DishEntity;
import com.example.weightcontrolapp.entity.MealEntity;
import com.example.weightcontrolapp.entity.UserEntity;
import com.example.weightcontrolapp.error.CustomException;
import com.example.weightcontrolapp.mapper.MealMapper;
import com.example.weightcontrolapp.repository.DishRepository;
import com.example.weightcontrolapp.repository.MealRepository;
import com.example.weightcontrolapp.repository.UserRepository;
import com.example.weightcontrolapp.service.impl.MealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static com.example.weightcontrolapp.constants.TestsVariables.TEST_CALORIES;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_DATE_TIME;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_DISH_NAME;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_ID;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MealServiceTest {

    @InjectMocks
    private MealServiceImpl mealService;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private MealMapper mealMapper;

    private MealRequest mealRequest;

    private MealEntity mealEntity;

    private MealResponse mealResponse;

    private UserEntity userEntity;

    private DishEntity dishEntity;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(TEST_UUID);

        dishEntity = new DishEntity();
        dishEntity.setId(TEST_ID);
        dishEntity.setName(TEST_DISH_NAME);
        dishEntity.setCalories(TEST_CALORIES);

        mealRequest = new MealRequest();
        mealRequest.setUserId(TEST_UUID.toString());
        mealRequest.setDishIds(List.of(TEST_ID));
        mealRequest.setDateTime(TEST_DATE_TIME);

        mealEntity = new MealEntity();
        mealEntity.setId(1L);
        mealEntity.setUser(userEntity);
        mealEntity.setDishes(List.of(dishEntity));
        mealEntity.setDateTime(TEST_DATE_TIME);

        mealResponse = new MealResponse();
        mealResponse.setId(1L);
        mealResponse.setUserId(TEST_UUID);
        mealResponse.setDishes(List.of(new PublicDishResponse()));
        mealResponse.setDateTime(TEST_DATE_TIME);
    }

    @Test
    void createMeal_Success() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.of(userEntity));
        when(dishRepository.findAllById(List.of(TEST_ID))).thenReturn(List.of(dishEntity));
        when(mealMapper.toMealEntity(mealRequest)).thenReturn(mealEntity);
        when(mealRepository.save(mealEntity)).thenReturn(mealEntity);
        when(mealMapper.toMealResponse(mealEntity)).thenReturn(mealResponse);

        MealResponse result = mealService.createMeal(mealRequest);

        assertThat(result).isEqualTo(mealResponse);
    }

    @Test
    void createMeal_UserNotFound() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class,
                () -> mealService.createMeal(mealRequest));

        assertThat(exception.getError()).isEqualTo(ErrorCodes.USER_NOT_FOUND);
    }

    @Test
    void createMeal_DishNotFound() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.of(userEntity));
        when(dishRepository.findAllById(List.of(TEST_ID))).thenReturn(List.of());

        CustomException exception = assertThrows(CustomException.class,
                () -> mealService.createMeal(mealRequest));

        assertThat(exception.getError()).isEqualTo(ErrorCodes.DISH_NOT_FOUND);
    }
}
