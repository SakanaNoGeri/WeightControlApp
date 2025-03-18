package com.example.weightcontrolapp.service.impl;

import com.example.weightcontrolapp.dto.request.DishRequest;
import com.example.weightcontrolapp.dto.response.PageableResponse;
import com.example.weightcontrolapp.dto.response.PublicDishResponse;
import com.example.weightcontrolapp.entity.DishEntity;
import com.example.weightcontrolapp.mapper.DishMapper;
import com.example.weightcontrolapp.repository.DishRepository;
import com.example.weightcontrolapp.service.DishService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    @Transactional
    @Override
    public PublicDishResponse createDish(DishRequest dishRequest) {
        DishEntity dishEntity = dishMapper.dishRequestToDishEntity(dishRequest);
        dishRepository.save(dishEntity);
        return dishMapper.dishEntityToPublicDishResponse(dishEntity);
    }

    @Override
    public PageableResponse<List<PublicDishResponse>> getAllDishesInfo(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DishEntity> dishPage = dishRepository.findAll(pageable);
        List<PublicDishResponse> content = dishMapper.toListPublicDishResponse(dishPage.getContent());
        return new PageableResponse<>(content, page, size, dishPage.getTotalElements(), dishPage.getTotalPages());
    }
}
