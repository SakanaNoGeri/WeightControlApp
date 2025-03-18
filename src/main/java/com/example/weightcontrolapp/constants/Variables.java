package com.example.weightcontrolapp.constants;

public interface Variables {

    String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

    String ERROR_MESSAGE_HEADER = "ErrorMessage";

    double BMR_BASE_MALE = 88.362;

    double BMR_WEIGHT_COEFF = 13.397;

    double BMR_HEIGHT_COEFF = 4.799;

    double BMR_AGE_COEFF = 5.677;

    double ACTIVITY_MODERATE = 1.375;

    double DEFAULT_CALORIE_NORM = 0.0;
}
