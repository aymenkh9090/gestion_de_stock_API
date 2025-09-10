package com.gestiondestock.handlers;

import com.gestiondestock.exceptions.EntityNotFoundException;
import com.gestiondestock.exceptions.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandlers extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception , WebRequest webRequestuest) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .Code(exception.getErrorsCode())
                .httpCode(status.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, status);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception , WebRequest webRequestuest) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .Code(exception.getErrorsCode())
                .httpCode(status.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, status);
    }




}
