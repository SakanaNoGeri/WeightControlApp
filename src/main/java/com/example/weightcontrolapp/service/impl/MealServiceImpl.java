package com.example.weightcontrolapp.service.impl;

import com.example.weightcontrolapp.constants.ErrorCodes;
import com.example.weightcontrolapp.dto.request.MealRequest;
import com.example.weightcontrolapp.dto.response.MealResponse;
import com.example.weightcontrolapp.entity.DishEntity;
import com.example.weightcontrolapp.entity.MealEntity;
import com.example.weightcontrolapp.entity.UserEntity;
import com.example.weightcontrolapp.error.CustomException;
import com.example.weightcontrolapp.mapper.MealMapper;
import com.example.weightcontrolapp.repository.DishRepository;
import com.example.weightcontrolapp.repository.MealRepository;
import com.example.weightcontrolapp.repository.UserRepository;
import com.example.weightcontrolapp.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    private final DishRepository dishRepository;

    private final MealMapper mealMapper;

    @Transactional
    @Override
    public MealResponse createMeal(MealRequest mealRequest) {
        UserEntity user = userRepository.findById(UUID.fromString(mealRequest.getUserId()))
                .orElseThrow(() -> new CustomException(ErrorCodes.USER_NOT_FOUND));

        List<DishEntity> dishes = dishRepository.findAllById(mealRequest.getDishIds());
        if (dishes.size() != mealRequest.getDishIds().size()) {
            throw new CustomException(ErrorCodes.DISH_NOT_FOUND);
        }

        MealEntity mealEntity = mealMapper.toMealEntity(mealRequest);
        mealEntity.setUser(user);
        mealEntity.setDishes(dishes);

        mealRepository.save(mealEntity);
        return mealMapper.toMealResponse(mealEntity);
    }
}
