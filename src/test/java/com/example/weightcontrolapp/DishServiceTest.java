package com.example.weightcontrolapp;

import com.example.weightcontrolapp.dto.request.DishRequest;
import com.example.weightcontrolapp.dto.response.PageableResponse;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import com.example.weightcontrolapp.entity.DishEntity;
import com.example.weightcontrolapp.mapper.DishMapper;
import com.example.weightcontrolapp.repository.DishRepository;
import com.example.weightcontrolapp.service.impl.DishServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;

import static com.example.weightcontrolapp.constants.TestsVariables.TEST_CALORIES;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_CARBS;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_DISH_NAME;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_FATS;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_ID;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_NUMBER_OF_ELEMENTS;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_PER_PAGE;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_PROTEIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {

    @InjectMocks
    private DishServiceImpl dishService;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishMapper dishMapper;

    private DishRequest dishRequest;

    private DishEntity dishEntity;

    private PublicDishResponse dishResponse;

    private Page<DishEntity> dishPage;

    @BeforeEach
    public void setUp() {
        dishRequest = new DishRequest();
        dishRequest.setName(TEST_DISH_NAME);
        dishRequest.setCalories(TEST_CALORIES);
        dishRequest.setProtein(TEST_PROTEIN);
        dishRequest.setFats(TEST_FATS);
        dishRequest.setCarbs(TEST_CARBS);

        dishEntity = new DishEntity();
        dishEntity.setId(TEST_ID);
        dishEntity.setName(TEST_DISH_NAME);
        dishEntity.setCalories(TEST_CALORIES);
        dishEntity.setProtein(TEST_PROTEIN);
        dishEntity.setFats(TEST_FATS);
        dishEntity.setCarbs(TEST_CARBS);

        dishResponse = new PublicDishResponse();
        dishResponse.setId(TEST_ID);
        dishResponse.setName(TEST_DISH_NAME);
        dishResponse.setCalories(TEST_CALORIES);
        dishResponse.setProtein(TEST_PROTEIN);
        dishResponse.setFats(TEST_FATS);
        dishResponse.setCarbs(TEST_CARBS);

        dishPage = new PageImpl<>(List.of(dishEntity), PageRequest.of(0, TEST_PER_PAGE), TEST_NUMBER_OF_ELEMENTS);
    }

    @Test
    void createDish_Success() {
        when(dishMapper.dishRequestToDishEntity(dishRequest)).thenReturn(dishEntity);
        when(dishRepository.save(dishEntity)).thenReturn(dishEntity);
        when(dishMapper.dishEntityToPublicDishResponse(dishEntity)).thenReturn(dishResponse);

        PublicDishResponse result = dishService.createDish(dishRequest);

        assertThat(result).isEqualTo(dishResponse);
    }

    @Test
    void getAllDishesInfo_Success() {
        when(dishRepository.findAll(PageRequest.of(0, 10))).thenReturn(dishPage);
        when(dishMapper.toListPublicDishResponse(List.of(dishEntity))).thenReturn(List.of(dishResponse));

        PageableResponse<List<PublicDishResponse>> result = dishService.getAllDishesInfo(0, 10);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(dishResponse);
        assertThat(result.getTotalElements()).isEqualTo(1);
    }
}
