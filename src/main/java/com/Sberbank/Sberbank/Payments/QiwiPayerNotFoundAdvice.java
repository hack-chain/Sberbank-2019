package com.Sberbank.Sberbank.Payments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QiwiPayerNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(QiwiPayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(QiwiPayerNotFoundException ex) {
        return ex.getMessage();
    }
}
