package com.example.weightcontrolapp.controller;

import com.example.weightcontrolapp.dto.request.UserRequest;
import com.example.weightcontrolapp.dto.response.UserResponse;
import com.example.weightcontrolapp.dto.response.commonResponse.CustomSuccessResponse;
import com.example.weightcontrolapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CustomSuccessResponse<UserResponse>> createUser(
            @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(userService.createUser(userRequest)));
    }
}
