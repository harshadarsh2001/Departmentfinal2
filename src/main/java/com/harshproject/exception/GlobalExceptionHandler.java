package com.harshproject.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.harshproject.util.DepartmentEmailAspect;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private final DepartmentEmailAspect departmentEmailAspect;

    public GlobalExceptionHandler(DepartmentEmailAspect departmentEmailAspect) {
        this.departmentEmailAspect = departmentEmailAspect;
    }

    @ExceptionHandler({ DepartmentNotFoundException.class, Exception.class })
    public ResponseEntity<Object> handleException(
            Exception ex, HttpServletRequest request) {
        // Send an email using DepartmentEmailAspect
        departmentEmailAspect.sendErrorEmail("GlobalExceptionHandler", ex.getMessage());

        // Create a custom error response
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError apiError = new ApiError(status, "Interval Server Error", LocalDateTime.now(), null);
        apiError.addError("error", ex.getMessage()); // Add the specific error message

        return new ResponseEntity<>(apiError, status);
    }
}
