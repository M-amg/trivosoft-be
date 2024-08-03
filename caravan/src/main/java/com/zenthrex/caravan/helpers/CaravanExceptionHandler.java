package com.zenthrex.caravan.helpers;

import com.zenthrex.caravan.exceptions.ValidationCaravanException;
import com.zenthrex.trivo.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CaravanExceptionHandler {
    @ExceptionHandler(ValidationCaravanException.class)
    public ResponseEntity<ErrorDto> handleCaravanValidationException(ValidationCaravanException ex) {
        ErrorDto errorDto = new ErrorDto()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }
}
