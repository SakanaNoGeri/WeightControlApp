package com.example.weightcontrolapp.dto.response.commonResponse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

import static com.example.weightcontrolapp.constants.ErrorCodes.errorCodesMap;
import static com.example.weightcontrolapp.constants.ValidationsConstants.UNKNOWN;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BaseSuccessResponse {

    private Integer statusCode = 0;

    private Boolean success = true;

    private String message = "Success";

    public BaseSuccessResponse(Integer statusCode) {
        this.statusCode = statusCode;
        this.success = statusCode == 0;
        this.message = errorCodesMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(statusCode))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(UNKNOWN);
    }
}
