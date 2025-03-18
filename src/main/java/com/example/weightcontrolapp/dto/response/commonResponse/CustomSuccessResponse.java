package com.example.weightcontrolapp.dto.response.commonResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.weightcontrolapp.constants.ErrorCodes.errorCodesMap;
import static com.example.weightcontrolapp.constants.ValidationsConstants.UNKNOWN;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class CustomSuccessResponse<T> {

    private T data;

    private Integer statusCode = 0;

    private final Boolean success = true;

    private List<String> messages;

    private List<Integer> codes;

    public CustomSuccessResponse(T data) {
        this.data = data;
    }

    public CustomSuccessResponse(Integer statusCode, List<Integer> codes) {
        this.statusCode = statusCode;
        this.codes = codes;
        this.messages = codes.stream()
                .map(code -> errorCodesMap.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(code))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(UNKNOWN))
                .collect(Collectors.toList());
    }
}
