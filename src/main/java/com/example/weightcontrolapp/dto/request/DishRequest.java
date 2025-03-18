package com.example.weightcontrolapp.dto.request;

import com.example.weightcontrolapp.constants.ValidationsConstants;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest {
    @NotBlank(message = ValidationsConstants.USER_NAME_HAS_TO_BE_PRESENT)
    @Size(min = 1, max = 100, message = ValidationsConstants.NAME_SIZE_NOT_VALID)
    private String name;

    @NotNull(message = ValidationsConstants.CALORIES_NOT_NULL)
    @PositiveOrZero(message = ValidationsConstants.CALORIES_NON_NEGATIVE)
    @DecimalMax(value = "10000.0", message = ValidationsConstants.CALORIES_MAX_NOT_VALID)
    private Double calories;

    @NotNull(message = ValidationsConstants.PROTEIN_NOT_NULL)
    @PositiveOrZero(message = ValidationsConstants.PROTEIN_NON_NEGATIVE)
    @DecimalMax(value = "1000.0", message = ValidationsConstants.NUTRIENT_MAX_NOT_VALID)
    private Double protein;

    @NotNull(message = ValidationsConstants.FATS_NOT_NULL)
    @PositiveOrZero(message = ValidationsConstants.FATS_NON_NEGATIVE)
    @DecimalMax(value = "1000.0", message = ValidationsConstants.NUTRIENT_MAX_NOT_VALID)
    private Double fats;

    @NotNull(message = ValidationsConstants.CARBS_NOT_NULL)
    @PositiveOrZero(message = ValidationsConstants.CARBS_NON_NEGATIVE)
    @DecimalMax(value = "1000.0", message = ValidationsConstants.NUTRIENT_MAX_NOT_VALID)
    private Double carbs;
}
