package com.example.weightcontrolapp.service;

import com.example.weightcontrolapp.dto.request.CreateUserRequest;
import com.example.weightcontrolapp.dto.response.CreateUserResponse;
import com.example.weightcontrolapp.dto.response.PublicUserResponse;
import com.example.weightcontrolapp.entity.UserEntity;
import java.util.List;
import java.util.UUID;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    PublicUserResponse getUserInfoById(String id);

    List<PublicUserResponse> getAllUsersInfo();

    UserEntity getUserEntityByIdOrThrow(UUID userId);
}
