package com.fitness.activityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundErrorHandler(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ErrorResponse errorResponse=new ErrorResponse(message,"failed");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotValidUserException.class)
    public ResponseEntity<ErrorResponse> notValidUserErrorHandler(NotValidUserException ex){
        String message = ex.getMessage();
        ErrorResponse errorResponse=new ErrorResponse(message,"failed");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
