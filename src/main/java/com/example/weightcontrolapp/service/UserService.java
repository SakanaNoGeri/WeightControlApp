package com.example.weightcontrolapp.service;

import com.example.weightcontrolapp.dto.request.CreateUserRequest;
import com.example.weightcontrolapp.dto.request.PutUserRequest;
import com.example.weightcontrolapp.dto.response.CreateUserResponse;
import com.example.weightcontrolapp.dto.response.PublicUserResponse;
import java.util.List;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    PublicUserResponse getUserInfoById(String id);

    List<PublicUserResponse> getAllUsersInfo();

    PublicUserResponse replaceUser(PutUserRequest putUserRequest);
}
