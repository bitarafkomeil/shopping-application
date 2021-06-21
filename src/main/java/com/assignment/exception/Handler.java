package com.assignment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        if (ex instanceof BadRequestException) {
            ExceptionResponse exceptionResponse = new ExceptionResponse();
            exceptionResponse.setDateTime(LocalDateTime.now());
            exceptionResponse.setMessage(ex.getMessage());
            exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            exceptionResponse.setPath(((BadRequestException) ex).getType());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}