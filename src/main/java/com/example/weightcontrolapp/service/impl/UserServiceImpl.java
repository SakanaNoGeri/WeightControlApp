package com.example.weightcontrolapp.service.impl;

import com.example.weightcontrolapp.constants.ErrorCodes;
import com.example.weightcontrolapp.dto.request.UserRequest;
import com.example.weightcontrolapp.dto.response.UserResponse;
import com.example.weightcontrolapp.entity.UserEntity;
import com.example.weightcontrolapp.error.CustomException;
import com.example.weightcontrolapp.mapper.UserMapper;
import com.example.weightcontrolapp.repository.UserRepository;
import com.example.weightcontrolapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.weightcontrolapp.constants.Variables.ACTIVITY_MODERATE;
import static com.example.weightcontrolapp.constants.Variables.BMR_AGE_COEFF;
import static com.example.weightcontrolapp.constants.Variables.BMR_BASE_MALE;
import static com.example.weightcontrolapp.constants.Variables.BMR_HEIGHT_COEFF;
import static com.example.weightcontrolapp.constants.Variables.BMR_WEIGHT_COEFF;
import static com.example.weightcontrolapp.constants.Variables.DEFAULT_CALORIE_NORM;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        userRepository.findByEmail(userRequest.getEmail())
                .ifPresent(userEntity -> {
                    throw new CustomException(ErrorCodes.USER_ALREADY_EXISTS);
                });
        UserEntity userEntity = userMapper.userRequestToUserEntity(userRequest);
        userEntity.setDailyCalorieNorm(evaluateDailyNorm(userEntity.getWeight(),
                userEntity.getHeight(),
                userEntity.getAge()));
        userRepository.save(userEntity);
        return userMapper.userEntityToUserResponse(userEntity);
    }

    public Double evaluateDailyNorm(Double weight, Double height, Integer age) {
        if (weight != null && height != null && age != null) {
            return (BMR_BASE_MALE + (BMR_WEIGHT_COEFF * weight) + (BMR_HEIGHT_COEFF * height) - (BMR_AGE_COEFF * age)) * ACTIVITY_MODERATE;
        } else {
            return DEFAULT_CALORIE_NORM;
        }
    }
}
