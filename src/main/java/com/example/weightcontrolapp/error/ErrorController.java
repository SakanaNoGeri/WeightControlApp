package com.example.weightcontrolapp.error;

import com.example.weightcontrolapp.constants.ErrorCodes;
import com.example.weightcontrolapp.constants.Variables;
import com.example.weightcontrolapp.dto.response.commonResponse.CustomSuccessResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomSuccessResponse> handle(HttpMessageNotReadableException e) {
        HttpHeaders errorHeader = new HttpHeaders();
        errorHeader.add(Variables.ERROR_MESSAGE_HEADER, ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getMessage());
        return ResponseEntity.badRequest()
                .headers(errorHeader)
                .body(new CustomSuccessResponse(ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getCode(),
                        List.of(ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getCode())));
    }

    @ExceptionHandler
    public ResponseEntity<CustomSuccessResponse> handle(CustomException e) {
        int errorCode = e.getError().getCode();
        HttpHeaders errorHeader = new HttpHeaders();

        errorHeader.add(Variables.ERROR_MESSAGE_HEADER, Arrays.stream(ErrorCodes.values())
                .filter(errorCodeEnum -> errorCodeEnum.getCode() == errorCode)
                .map(ErrorCodes::getMessage).findFirst()
                .orElseThrow(() -> new CustomException(ErrorCodes.UNKNOWN)));

        return ResponseEntity.badRequest()
                .headers(errorHeader)
                .body(new CustomSuccessResponse<>(ErrorCodes.errorCodesMap
                        .get(e.getError().getMessage()),
                        List.of(ErrorCodes.errorCodesMap.get(e.getError().getMessage()))));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomSuccessResponse> handle(ConstraintViolationException e) {
        HttpHeaders headers = new HttpHeaders();

        headers.add(Variables.ERROR_MESSAGE_HEADER, e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCodes.UNKNOWN)));

        List<Integer> errorCodes = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .map(ErrorCodes.errorCodesMap::get)
                .toList();

        return ResponseEntity.badRequest()
                .headers(headers)
                .body(new CustomSuccessResponse<>(errorCodes.get(0), errorCodes));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomSuccessResponse> handle(MethodArgumentNotValidException e) {
        HttpHeaders errorHeader = new HttpHeaders();
        errorHeader.add(Variables.ERROR_MESSAGE_HEADER, e.getBindingResult()
                .getAllErrors()
                .stream().map(objectError -> objectError.getDefaultMessage())
                .filter(Objects::nonNull)
                .findFirst().orElseThrow(() -> new CustomException(ErrorCodes.UNKNOWN)));

        List<Integer> errorCodes = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> ErrorCodes.errorCodesMap.get(objectError.getDefaultMessage()))
                .filter(Objects::nonNull)
                .toList();

        return ResponseEntity.badRequest()
                .headers(errorHeader)
                .body(new CustomSuccessResponse<>(errorCodes.get(0), errorCodes));
    }

}
