package com.example.weightcontrolapp;

import com.example.weightcontrolapp.constants.ErrorCodes;
import com.example.weightcontrolapp.dto.request.UserRequest;
import com.example.weightcontrolapp.dto.response.UserResponse;
import com.example.weightcontrolapp.entity.UserEntity;
import com.example.weightcontrolapp.error.CustomException;
import com.example.weightcontrolapp.mapper.UserMapper;
import com.example.weightcontrolapp.repository.UserRepository;
import com.example.weightcontrolapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static com.example.weightcontrolapp.constants.Goal.WEIGHT_LOSS;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_EMAIL;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_NAME;
import static com.example.weightcontrolapp.constants.TestsVariables.TEST_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserRequest userRequest;

    private UserEntity userEntity;

    private UserResponse userResponse;

    @BeforeEach
    public void setUp() {
        userRequest = new UserRequest();
        userRequest.setEmail(TEST_EMAIL);
        userRequest.setName(TEST_NAME);
        userRequest.setAge(25);
        userRequest.setWeight(80.5);
        userRequest.setHeight(175.0);
        userRequest.setGoal(WEIGHT_LOSS);

        userEntity = new UserEntity();
        userEntity.setId(TEST_UUID);
        userEntity.setEmail(TEST_EMAIL);
        userEntity.setName(TEST_NAME);
        userEntity.setAge(25);
        userEntity.setWeight(80.5);
        userEntity.setHeight(175.0);
        userEntity.setGoal(WEIGHT_LOSS);
        userEntity.setDailyCalorieNorm(2563.9906875);

        userResponse = new UserResponse();
        userResponse.setId(TEST_UUID);
        userResponse.setEmail(TEST_EMAIL);
        userResponse.setName(TEST_NAME);
        userResponse.setAge(25);
        userResponse.setWeight(80.5);
        userResponse.setHeight(175.0);
        userResponse.setGoal(WEIGHT_LOSS);
        userResponse.setDailyCalorieNorm(2563.9906875);
    }

    @Test
    void createUser_Success() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        when(userMapper.toUserEntity(userRequest)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toUserResponse(userEntity)).thenReturn(userResponse);

        UserResponse result = userService.createUser(userRequest);

        assertThat(result).isEqualTo(userResponse);
        assertThat(result.getDailyCalorieNorm()).isEqualTo(2563.9906875);
    }

    @Test
    void createUser_UserAlreadyExists() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(userEntity));

        CustomException exception = assertThrows(CustomException.class,
                () -> userService.createUser(userRequest));

        assertThat(exception.getError()).isEqualTo(ErrorCodes.USER_ALREADY_EXISTS);
    }
}