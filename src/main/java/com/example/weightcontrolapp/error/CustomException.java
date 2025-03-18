package com.example.weightcontrolapp.error;

import com.example.weightcontrolapp.constants.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCodes error;
}
