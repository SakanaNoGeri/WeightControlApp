package com.example.weightcontrolapp.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HistoryResponse {
    private Map<LocalDate, List<MealResponse>> mealsByDay;
}
