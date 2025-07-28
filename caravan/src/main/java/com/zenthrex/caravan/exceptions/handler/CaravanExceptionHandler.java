package com.zenthrex.caravan.exceptions.handler;

import com.zenthrex.core.exception.ResourceNotFoundException;
import com.zenthrex.core.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CaravanExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFound(ResourceNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
