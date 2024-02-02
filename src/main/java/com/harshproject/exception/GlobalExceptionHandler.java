package com.harshproject.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<Object> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        // Log the exception
        ex.printStackTrace();

        HttpStatus status = HttpStatus.NOT_IMPLEMENTED;
        ApiError apiError = new ApiError(status, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        // Log the exception
        ex.printStackTrace();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError apiError = new ApiError(status, "An unexpected error occurred", LocalDateTime.now());
        return new ResponseEntity<>(apiError, status);
    }
}

