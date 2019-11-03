package com.teste.vianuvem.exception.config;

import com.teste.vianuvem.exception.BadRequestException;
import com.teste.vianuvem.exception.PlanetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
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


    @Override
    protected ResponseEntity handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        log.error(ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null, null, ex);

    }

    @ExceptionHandler({IllegalArgumentException.class, BadRequestException.class})
    private ResponseEntity<?> handleBadRequestException(Throwable ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null, null, ex);
    }

    @ExceptionHandler({ PlanetNotFoundException.class})
    private ResponseEntity<?> handleResourceNotFoundException(Throwable ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null, null, ex);
    }

    @ExceptionHandler({Exception.class})
    private ResponseEntity<?> handleOtherExceptions(Exception ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null, null, ex);
    }

}
