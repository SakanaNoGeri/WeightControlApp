package com.example.weightcontrolapp.service;

import com.example.weightcontrolapp.dto.request.UserRequest;
import com.example.weightcontrolapp.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
}
