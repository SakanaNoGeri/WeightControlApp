package com.example.weightcontrolapp.service;

import com.example.weightcontrolapp.dto.request.MealRequest;
import com.example.weightcontrolapp.dto.response.MealResponse;

public interface MealService {

    MealResponse createMeal(MealRequest mealRequest);
}
