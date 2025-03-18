package com.example.weightcontrolapp.mapper;

import com.example.weightcontrolapp.dto.request.MealRequest;
import com.example.weightcontrolapp.dto.response.MealResponse;
import com.example.weightcontrolapp.entity.MealEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DishMapper.class})
public interface MealMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    MealEntity mealRequestToMealEntity(MealRequest request);

    @Mapping(target = "userId", source = "user.id")
    MealResponse mealEntityToMealResponse(MealEntity entity);
}
