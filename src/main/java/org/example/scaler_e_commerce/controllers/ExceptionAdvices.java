package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.ErrorResponseDto;
import org.example.scaler_e_commerce.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto.getMessage(), HttpStatus.NOT_FOUND);
    }
}
