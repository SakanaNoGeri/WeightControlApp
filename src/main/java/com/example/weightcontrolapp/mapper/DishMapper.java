package com.example.weightcontrolapp.mapper;

import com.example.weightcontrolapp.dto.request.DishRequest;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import com.example.weightcontrolapp.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(target = "id", ignore = true)
    DishEntity dishRequestToDishEntity(DishRequest request);

    PublicDishResponse toPublicDishResponse(DishEntity entity);

    List<PublicDishResponse> toListPublicDishResponse(List<DishEntity> entities);
}
