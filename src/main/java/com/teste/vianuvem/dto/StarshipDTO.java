package com.teste.vianuvem.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class StarshipDTO {


    private String name;
    private String model;
    private String manufacturer;
    private String url;
    private LocalDateTime created;

}
