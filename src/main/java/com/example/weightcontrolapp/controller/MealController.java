package com.example.weightcontrolapp.controller;

import com.example.weightcontrolapp.dto.request.MealRequest;
import com.example.weightcontrolapp.dto.response.MealResponse;
import com.example.weightcontrolapp.dto.response.commonResponse.CustomSuccessResponse;
import com.example.weightcontrolapp.service.MealService;
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
@RequestMapping("/meal")
@Validated
public class MealController {
    private final MealService mealService;

    @PostMapping
    public ResponseEntity<CustomSuccessResponse<MealResponse>> createMeal(
            @RequestBody @Valid MealRequest mealRequest) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(mealService.createMeal(mealRequest)));
    }
}
