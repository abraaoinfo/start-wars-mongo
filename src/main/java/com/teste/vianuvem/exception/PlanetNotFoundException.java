package com.teste.vianuvem.exception;

public class PlanetNotFoundException extends RuntimeException{
    public PlanetNotFoundException(String message) {
        super(message);
    }
}
