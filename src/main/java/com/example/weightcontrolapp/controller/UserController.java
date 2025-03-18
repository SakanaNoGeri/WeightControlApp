package com.example.weightcontrolapp.controller;

import com.example.weightcontrolapp.constants.ValidationsConstants;
import com.example.weightcontrolapp.dto.request.CreateUserRequest;
import com.example.weightcontrolapp.dto.request.PutUserRequest;
import com.example.weightcontrolapp.dto.response.CreateUserResponse;
import com.example.weightcontrolapp.dto.response.PublicUserResponse;
import com.example.weightcontrolapp.dto.response.commonResponse.BaseSuccessResponse;
import com.example.weightcontrolapp.dto.response.commonResponse.CustomSuccessResponse;
import com.example.weightcontrolapp.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static com.example.weightcontrolapp.constants.Variables.UUID_REGEX;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CustomSuccessResponse<CreateUserResponse>> createUser(@RequestBody
                                                                                 @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(userService.createUser(createUserRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomSuccessResponse<PublicUserResponse>> getUserInfoById(@PathVariable
                                                                 @Pattern(regexp = UUID_REGEX,
                                                                         message = ValidationsConstants
                                                                                 .MAX_UPLOAD_SIZE_EXCEEDED)
                                                                 String id) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(userService.getUserInfoById(id)));
    }

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<List<PublicUserResponse>>> getAllUsersInfo() {
        return ResponseEntity.ok(new CustomSuccessResponse<>(userService.getAllUsersInfo()));
    }

    @PutMapping
    public ResponseEntity<CustomSuccessResponse<PublicUserResponse>> replaceUser(@RequestBody
                                                             @Valid PutUserRequest dto) {
//        return ResponseEntity.ok(new CustomSuccessResponse<>(userService.replaceUser(dto)));
        return null;
    }

    @DeleteMapping
    public ResponseEntity<BaseSuccessResponse> deleteUser() {
        return ResponseEntity.ok(new BaseSuccessResponse());
    }
}
