package com.example.weightcontrolapp.constants;

public interface ValidationsConstants {
    String UNKNOWN = "Unknown error code";

    String USERNAME_SIZE_NOT_VALID = "Username size should be between 3 and 25";

    String USER_EMAIL_NOT_VALID = "user email must be a well-formed email address";

    String USER_NOT_FOUND = "Could not find user";

    String USER_EMAIL_NOT_NULL = "user email mustn't be null";

    String EMAIL_SIZE_NOT_VALID = "email size not valid";

    String USER_NAME_HAS_TO_BE_PRESENT = "User name has to be present";

    String HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "Http request not valid";

    String DISH_NOT_FOUND = "Dish not found";

    String WEIGHT_MIN_NOT_VALID = "Weight must be at least 1 kg";

    String WEIGHT_MAX_NOT_VALID = "Weight must be less than 500 kg";

    String HEIGHT_MIN_NOT_VALID = "Height must be at least 50 cm";

    String HEIGHT_MAX_NOT_VALID = "Height must be less than 300 cm";

    String AGE_MIN_NOT_VALID = "Age must be at least 1";

    String AGE_MAX_NOT_VALID = "Age must be less than 150";

    String CALORIES_MAX_NOT_VALID = "Calories must be less than 10000";

    String NUTRIENT_MAX_NOT_VALID = "Nutrient must be less than 1000";

    String NAME_SIZE_NOT_VALID = "Name must be between 1 and 100 characters";

    String INVALID_UUID_FORMAT = "Invalid UUID format";

    String GOAL_MUST_NOT_BE_NULL = "Goal must not be null";

    String CALORIES_NOT_NULL = "Calories must not be null";

    String PROTEIN_NOT_NULL = "Protein must not be null";

    String FATS_NOT_NULL = "Fats must not be null";

    String CARBS_NOT_NULL = "Carbs must not be null";

    String CALORIES_NON_NEGATIVE = "Calories must be non-negative";

    String PROTEIN_NON_NEGATIVE = "Protein must be non-negative";

    String FATS_NON_NEGATIVE = "Fats must be non-negative";

    String CARBS_NON_NEGATIVE = "Carbs must be non-negative";

    String USER_ID_NOT_NULL = "User ID must not be null";

    String DISH_IDS_NOT_EMPTY = "Dish IDs must not be empty";

    String DATE_TIME_NOT_NULL = "DateTime must not be null";
}
