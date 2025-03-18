package com.example.weightcontrolapp.dto.request;

import com.example.weightcontrolapp.constants.ValidationsConstants;
import com.example.weightcontrolapp.constants.Variables;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MealRequest {
    @NotNull(message = ValidationsConstants.USER_ID_NOT_NULL)
    @Pattern(regexp = Variables.UUID_REGEX, message = ValidationsConstants.INVALID_UUID_FORMAT)
    private String userId;

    @NotEmpty(message = ValidationsConstants.DISH_IDS_NOT_EMPTY)
    private List<Long> dishIds;

    @NotNull(message = ValidationsConstants.DATE_TIME_NOT_NULL)
    private LocalDateTime dateTime;
}
