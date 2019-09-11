package com.teste.pamcary.handler;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ErrorResponse {
    private String exception;
    private String error;
    private String message;
    private Integer status;
    private Collection<String> errors;
}
