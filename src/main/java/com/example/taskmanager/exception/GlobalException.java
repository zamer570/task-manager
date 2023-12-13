package com.example.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> resourceNotFound(ResourceNotFound resourceNotFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFound.getMessage());

    }
}
