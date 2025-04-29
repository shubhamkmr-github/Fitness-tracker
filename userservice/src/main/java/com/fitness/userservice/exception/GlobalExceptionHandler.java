package com.fitness.userservice.exception;

import com.fitness.userservice.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ErrorResponse errorReponse=new ErrorResponse(message,false);

        return new ResponseEntity<ErrorResponse>(errorReponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ExistingUserException.class)
    public ResponseEntity<ErrorResponse> existingUserExceptionHandler(ExistingUserException ex) {
        String message = ex.getMessage();
        ErrorResponse errorReponse=new ErrorResponse(message,false);
        return new ResponseEntity<ErrorResponse>(errorReponse,HttpStatus.BAD_REQUEST);
    }
}
