package com.example.weightcontrolapp.service.impl;

import com.example.weightcontrolapp.constants.ErrorCodes;
import com.example.weightcontrolapp.dto.response.DailyReportResponse;
import com.example.weightcontrolapp.dto.response.HistoryResponse;
import com.example.weightcontrolapp.dto.response.MealResponse;
import com.example.weightcontrolapp.dto.response.NormCheckResponse;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import com.example.weightcontrolapp.entity.MealEntity;
import com.example.weightcontrolapp.entity.UserEntity;
import com.example.weightcontrolapp.error.CustomException;
import com.example.weightcontrolapp.mapper.MealMapper;
import com.example.weightcontrolapp.repository.MealRepository;
import com.example.weightcontrolapp.repository.UserRepository;
import com.example.weightcontrolapp.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    private final MealMapper mealMapper;

    @Override
    public DailyReportResponse getDailyReport(UUID userId, LocalDate date) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<MealEntity> meals = mealRepository.findByUserAndDateTimeBetween(user, startOfDay, endOfDay);
        List<MealResponse> mealResponses = meals.stream()
                .map(mealMapper::mealEntityToMealResponse)
                .collect(Collectors.toList());

        Double totalCalories = mealResponses.stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToDouble(PublicDishResponse::getCalories)
                .sum();

        DailyReportResponse response = new DailyReportResponse();
        response.setTotalCalories(totalCalories);
        response.setMeals(mealResponses);
        return response;
    }

    @Override
    public NormCheckResponse checkDailyNorm(UUID userId, LocalDate date) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<MealEntity> meals = mealRepository.findByUserAndDateTimeBetween(user, startOfDay, endOfDay);
        List<MealResponse> mealResponses = meals.stream()
                .map(mealMapper::mealEntityToMealResponse)
                .toList();

        Double totalCalories = mealResponses.stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToDouble(PublicDishResponse::getCalories)
                .sum();

        NormCheckResponse response = new NormCheckResponse();
        response.setTotalCalories(totalCalories);
        response.setDailyCalorieNorm(user.getDailyCalorieNorm());
        response.setWithinNorm(totalCalories <= user.getDailyCalorieNorm());
        return response;
    }

    @Override
    public HistoryResponse getMealHistory(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        List<MealEntity> meals = mealRepository.findByUser(user);
        Map<LocalDate, List<MealResponse>> mealsByDay = meals.stream()
                .map(mealMapper::mealEntityToMealResponse)
                .collect(Collectors.groupingBy(
                        meal -> meal.getDateTime().toLocalDate(),
                        Collectors.toList()
                ));

        HistoryResponse response = new HistoryResponse();
        response.setMealsByDay(mealsByDay);
        return response;
    }
}
