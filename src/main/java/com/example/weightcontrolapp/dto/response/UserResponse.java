package com.example.weightcontrolapp.dto.response;

import com.example.weightcontrolapp.constants.Goal;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private UUID id;

    private String email;

    private String name;

    private Integer age;

    private Double weight;

    private Double height;

    private Goal goal;

    private Double dailyCalorieNorm;
}
