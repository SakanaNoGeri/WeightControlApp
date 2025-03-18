package com.example.weightcontrolapp;

import com.example.weightcontrolapp.constants.ErrorCodes;
import com.example.weightcontrolapp.dto.response.DailyReportResponse;
import com.example.weightcontrolapp.dto.response.HistoryResponse;
import com.example.weightcontrolapp.dto.response.MealResponse;
import com.example.weightcontrolapp.dto.response.NormCheckResponse;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import com.example.weightcontrolapp.entity.DishEntity;
import com.example.weightcontrolapp.entity.MealEntity;
import com.example.weightcontrolapp.entity.UserEntity;
import com.example.weightcontrolapp.error.CustomException;
import com.example.weightcontrolapp.mapper.MealMapper;
import com.example.weightcontrolapp.repository.MealRepository;
import com.example.weightcontrolapp.repository.UserRepository;
import com.example.weightcontrolapp.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.weightcontrolapp.constants.TestsVariables.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MealMapper mealMapper;

    private UserEntity userEntity;

    private DishEntity dishEntity;

    private MealEntity mealEntity;

    private MealResponse mealResponse;

    private LocalDate testDate;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(TEST_UUID);
        userEntity.setDailyCalorieNorm(2563.9906875);

        dishEntity = new DishEntity();
        dishEntity.setId(TEST_ID);
        dishEntity.setName(TEST_DISH_NAME);
        dishEntity.setCalories(TEST_CALORIES);

        mealEntity = new MealEntity();
        mealEntity.setId(1L);
        mealEntity.setUser(userEntity);
        mealEntity.setDishes(List.of(dishEntity));
        mealEntity.setDateTime(TEST_DATE_TIME);

        mealResponse = new MealResponse();
        mealResponse.setId(1L);
        mealResponse.setUserId(TEST_UUID);
        mealResponse.setDishes(List.of(new PublicDishResponse() {{
            setId(TEST_ID);
            setName(TEST_DISH_NAME);
            setCalories(TEST_CALORIES);
        }}));
        mealResponse.setDateTime(TEST_DATE_TIME);

        testDate = TEST_DATE_TIME.toLocalDate();
    }

    @Test
    void getDailyReport_Success() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.of(userEntity));
        when(mealRepository.findByUserAndDateTimeBetween(
                userEntity, testDate.atStartOfDay(), testDate.atTime(LocalTime.MAX)))
                .thenReturn(List.of(mealEntity));
        when(mealMapper.toMealResponse(mealEntity)).thenReturn(mealResponse);

        DailyReportResponse result = reportService.getDailyReport(TEST_UUID, testDate);

        assertThat(result.getTotalCalories()).isEqualTo(TEST_CALORIES);
        assertThat(result.getMeals()).hasSize(1);
        assertThat(result.getMeals().get(0)).isEqualTo(mealResponse);
    }

    @Test
    void getDailyReport_UserNotFound() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class,
                () -> reportService.getDailyReport(TEST_UUID, testDate));

        assertThat(exception.getError()).isEqualTo(ErrorCodes.USER_NOT_FOUND);
    }

    @Test
    void checkDailyNorm_Success() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.of(userEntity));
        when(mealRepository.findByUserAndDateTimeBetween(
                userEntity, testDate.atStartOfDay(), testDate.atTime(LocalTime.MAX)))
                .thenReturn(List.of(mealEntity));
        when(mealMapper.toMealResponse(mealEntity)).thenReturn(mealResponse);

        NormCheckResponse result = reportService.checkDailyNorm(TEST_UUID, testDate);

        assertThat(result.getTotalCalories()).isEqualTo(TEST_CALORIES);
        assertThat(result.getDailyCalorieNorm()).isEqualTo(2563.9906875);
        assertThat(result.getWithinNorm()).isTrue();
    }

    @Test
    void checkDailyNorm_UserNotFound() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class,
                () -> reportService.checkDailyNorm(TEST_UUID, testDate));

        assertThat(exception.getError()).isEqualTo(ErrorCodes.USER_NOT_FOUND);
    }

    @Test
    void getMealHistory_Success() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.of(userEntity));
        when(mealRepository.findByUser(userEntity)).thenReturn(List.of(mealEntity));
        when(mealMapper.toMealResponse(mealEntity)).thenReturn(mealResponse);

        HistoryResponse result = reportService.getMealHistory(TEST_UUID);

        Map<LocalDate, List<MealResponse>> mealsByDay = result.getMealsByDay();
        assertThat(mealsByDay).hasSize(1);
        assertThat(mealsByDay.get(testDate)).hasSize(1);
        assertThat(mealsByDay.get(testDate).get(0)).isEqualTo(mealResponse);
    }

    @Test
    void getMealHistory_UserNotFound() {
        when(userRepository.findById(TEST_UUID)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class,
                () -> reportService.getMealHistory(TEST_UUID));

        assertThat(exception.getError()).isEqualTo(ErrorCodes.USER_NOT_FOUND);
    }
}
