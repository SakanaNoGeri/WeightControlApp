package com.example.weightcontrolapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NormCheckResponse {

    private Double totalCalories;

    private Double dailyCalorieNorm;

    private Boolean withinNorm;
}
