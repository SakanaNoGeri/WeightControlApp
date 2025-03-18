package com.example.weightcontrolapp.repository;

import com.example.weightcontrolapp.entity.MealEntity;
import com.example.weightcontrolapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {
    List<MealEntity> findByUserAndDateTimeBetween(UserEntity user, LocalDateTime start, LocalDateTime end);
    List<MealEntity> findByUser(UserEntity user);
}
