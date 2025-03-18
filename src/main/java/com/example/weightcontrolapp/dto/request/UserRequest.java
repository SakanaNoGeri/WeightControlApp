package com.example.weightcontrolapp.dto.request;

import com.example.weightcontrolapp.constants.Goal;
import com.example.weightcontrolapp.constants.ValidationsConstants;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @Size(min = 3, max = 100, message = ValidationsConstants.EMAIL_SIZE_NOT_VALID)
    @NotBlank(message = ValidationsConstants.USER_EMAIL_NOT_NULL)
    @Email(message = ValidationsConstants.USER_EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = ValidationsConstants.USER_NAME_HAS_TO_BE_PRESENT)
    @Size(min = 3, max = 25, message = ValidationsConstants.USERNAME_SIZE_NOT_VALID)
    private String name;

    @Min(value = 1, message = ValidationsConstants.AGE_MIN_NOT_VALID)
    @Max(value = 150, message = ValidationsConstants.AGE_MAX_NOT_VALID)
    private Integer age;

    @DecimalMin(value = "1.0", message = ValidationsConstants.WEIGHT_MIN_NOT_VALID)
    @DecimalMax(value = "500.0", message = ValidationsConstants.WEIGHT_MAX_NOT_VALID)
    private Double weight;

    @DecimalMin(value = "50.0", message = ValidationsConstants.HEIGHT_MIN_NOT_VALID)
    @DecimalMax(value = "300.0", message = ValidationsConstants.HEIGHT_MAX_NOT_VALID)
    private Double height;

    @NotNull(message = ValidationsConstants.GOAL_MUST_NOT_BE_NULL)
    private Goal goal;
}
