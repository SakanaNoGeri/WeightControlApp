package com.example.weightcontrolapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicDishResponse {

    private Long id;

    private String name;

    private Double calories;

    private Double protein;

    private Double fats;

    private Double carbs;
}
