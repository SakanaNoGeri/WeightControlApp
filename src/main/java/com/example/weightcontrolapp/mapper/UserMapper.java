package com.example.weightcontrolapp.mapper;

import com.example.weightcontrolapp.dto.request.UserRequest;
import com.example.weightcontrolapp.dto.response.UserResponse;
import com.example.weightcontrolapp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dailyCalorieNorm", ignore = true)
    UserEntity userRequestToUserEntity(UserRequest request);

    UserResponse userEntityToUserResponse(UserEntity entity);
}
