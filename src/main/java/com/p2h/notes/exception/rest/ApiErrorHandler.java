package com.p2h.notes.exception.rest;

import com.p2h.notes.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
class ApiErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ApiErrorResponse exceptionConstraintHandler(ConstraintViolationException e) {

        log.error("Error model validation", e);

        return ApiErrorResponse.buildForConstraintViolation(e);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> exceptionHandler(Exception e) {

        String message = e.getMessage();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiErrorResponse errorResponse = ApiErrorResponse.buildForGlobalException(e);

        if (e instanceof BaseException) {
            status =  ((BaseException) e).getHttpStatus();
            errorResponse.setCode(((BaseException) e).getCode());

        } else if (e instanceof BaseException) {
            status = HttpStatus.NOT_FOUND;
            errorResponse.setCode(status.value());

        } else if (e.getCause() instanceof BaseException) {
            BaseException causeException = (BaseException) e.getCause();
            status = causeException.getHttpStatus();
            errorResponse.setCode(causeException.getCode());
        }

        log.error("Internal server error. Cause:[" + message + "]", e);

        return new ResponseEntity<>(errorResponse, status);
    }
}
