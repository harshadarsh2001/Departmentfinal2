package com.harshproject.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors = new HashMap<>();

    // Getter and Setter methods for errors

    public void addError(String key, String value) {
        errors.put(key, value);
    }
}
