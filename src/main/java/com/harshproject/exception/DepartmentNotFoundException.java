package com.harshproject.exception;
import org.springframework.http.HttpStatusCode;

public class DepartmentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DepartmentNotFoundException(HttpStatusCode notImplemented, String message) {
        super(message);
    }
}
