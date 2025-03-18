package com.example.weightcontrolapp.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    UNKNOWN(1, ValidationsConstants.UNKNOWN),
    USER_NOT_FOUND(6, ValidationsConstants.USER_NOT_FOUND),
    USER_ALREADY_EXISTS(31, "User already exists"),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(46, ValidationsConstants.HTTP_MESSAGE_NOT_READABLE_EXCEPTION),
    DISH_NOT_FOUND(54, ValidationsConstants.DISH_NOT_FOUND);

    public static final Map<String, Integer> errorCodesMap = new HashMap<>();

    private final Integer code;

    private final String message;

    static {
        for (var elem : ErrorCodes.values()) {
            errorCodesMap.put(elem.getMessage(), elem.getCode());
        }
    }
}
