package com.alten.api.shop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class AltenExceptionControllerAdvice {

    @ExceptionHandler(value = {AltenException.class})
    public ResponseEntity<AltenErrorMessage> altenException(AltenException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(new AltenErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        ), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AltenErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(new AltenErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
