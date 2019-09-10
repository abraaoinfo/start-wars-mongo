package com.teste.pamcary.exception;

public class AlunoNotFoundException extends RuntimeException{
    public AlunoNotFoundException(String message) {
        super(message);
    }
}
