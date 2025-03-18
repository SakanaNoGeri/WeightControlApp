package com.example.weightcontrolapp.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MealResponse {

    private Long id;

    private UUID userId;

    private List<PublicDishResponse> dishes;

    private LocalDateTime dateTime;
}
