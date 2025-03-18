package com.example.weightcontrolapp.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Goal {
    WEIGHT_LOSS("Похудение"),
    MAINTENANCE("Поддержание"),
    MUSCLE_GAIN("Набор массы");

    private final String displayName;
}
