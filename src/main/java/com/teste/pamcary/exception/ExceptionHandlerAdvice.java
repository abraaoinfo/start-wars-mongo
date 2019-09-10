package com.teste.pamcary.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private ResponseEntity<?> createErrorResponse(HttpStatus status, String error, String message, List<String> errors, Throwable ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(error).message(message).errors(errors)
                .status(status.value()).build();

        log.error(message, ex);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    private ResponseEntity<?> handleBadRequestException(Throwable ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null, null, ex);
    }

    @ExceptionHandler({AlunoNotFoundException.class, EmptyResultDataAccessException.class})
    private ResponseEntity<?> handleOtherExceptions(Exception ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null, null, ex);
    }


}
