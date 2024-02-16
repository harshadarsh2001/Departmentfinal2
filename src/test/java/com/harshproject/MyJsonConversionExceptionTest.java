package com.harshproject;
import org.junit.jupiter.api.Test;

import com.harshproject.exception.MyJsonConversionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MyJsonConversionExceptionTest {

    @Test
    void testConstructorWithMessageAndCause() {
        String errorMessage = "Test error message";
        Throwable cause = new RuntimeException("Test cause");

        MyJsonConversionException exception = new MyJsonConversionException(errorMessage, cause);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithNullMessageAndCause() {
        MyJsonConversionException exception = new MyJsonConversionException(null, null);

        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    // Add more test cases as needed for your specific requirements

}
