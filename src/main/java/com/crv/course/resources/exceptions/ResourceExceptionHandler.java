package com.crv.course.resources.exceptions;

import com.crv.course.services.exceptions.DataBaseException;
import com.crv.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest req) {
        return createStandardError("Resource not found", HttpStatus.NOT_FOUND, e, req);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest req) {
        return createStandardError("Database error", HttpStatus.BAD_REQUEST, e, req);
    }

    private static ResponseEntity<StandardError> createStandardError(String error,
                                HttpStatus status, Exception e, HttpServletRequest req) {
        return ResponseEntity.status(status).body(
                new StandardError(
                    Instant.now(),
                    status.value(),
                    error,
                    e.getMessage(),
                    req.getRequestURI()
                )
        );
    }

}
