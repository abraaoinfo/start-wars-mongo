package com.teste.vianuvem.exception;

public class CallApiStartWarsException extends RuntimeException {

    public CallApiStartWarsException(String message) {
        super(message);
    }

    public CallApiStartWarsException(String message, Throwable cause) {
        super(message, cause);
    }


}
