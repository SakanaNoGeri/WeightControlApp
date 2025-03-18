package com.example.weightcontrolapp.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class DailyReportResponse {
    private Double totalCalories;

    private List<MealResponse> meals;
}
