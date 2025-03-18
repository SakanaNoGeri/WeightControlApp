package com.example.weightcontrolapp.constants;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TestsVariables {

    String TEST_EMAIL = "oleja@mail.ru";

    String TEST_NAME = "Олег";

    Long TEST_ID = 1L;

    UUID TEST_UUID = UUID.randomUUID();

    Long TEST_NUMBER_OF_ELEMENTS = 2L;

    Integer TEST_PER_PAGE = 10;

    String TEST_DISH_NAME = "Овсянка";

    double TEST_CALORIES = 200.0;

    double TEST_PROTEIN = 5.0;

    double TEST_FATS = 3.0;

    double TEST_CARBS = 35.0;

    LocalDateTime TEST_DATE_TIME = LocalDateTime.now();
}
