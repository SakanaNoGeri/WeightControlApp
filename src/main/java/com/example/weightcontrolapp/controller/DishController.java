package com.example.weightcontrolapp.controller;

import com.example.weightcontrolapp.dto.request.DishRequest;
import com.example.weightcontrolapp.dto.response.PageableResponse;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import com.example.weightcontrolapp.dto.response.commonResponse.CustomSuccessResponse;
import com.example.weightcontrolapp.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dish")
@Validated
public class DishController {
    private final DishService dishService;

    @PostMapping
    public ResponseEntity<CustomSuccessResponse<PublicDishResponse>> createDish(
            @RequestBody @Valid DishRequest dishRequest) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(dishService.createDish(dishRequest)));
    }

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<PageableResponse<List<PublicDishResponse>>>> getAllDishesInfo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int perPage) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(dishService.getAllDishesInfo(page, perPage)));
    }
}
