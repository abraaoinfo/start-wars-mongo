package com.teste.vianuvem.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Starship {

    private String name;
    private String model;
    private String manufacturer;
    private String url;
    private LocalDateTime created;


}
