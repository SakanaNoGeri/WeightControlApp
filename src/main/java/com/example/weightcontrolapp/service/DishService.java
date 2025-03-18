package com.example.weightcontrolapp.service;

import com.example.weightcontrolapp.dto.request.DishRequest;
import com.example.weightcontrolapp.dto.response.PageableResponse;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import java.util.List;

public interface DishService {

    PublicDishResponse createDish(DishRequest dishRequest);

    PageableResponse<List<PublicDishResponse>> getAllDishesInfo(int page, int perPage);
}
