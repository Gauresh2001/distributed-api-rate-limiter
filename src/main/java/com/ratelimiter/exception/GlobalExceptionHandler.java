package com.ratelimiter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        HashMap<String, Object> error = new HashMap<>();
        error.put("status", 400);
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        HashMap<String, Object> error = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> error.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
