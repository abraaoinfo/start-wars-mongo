package com.teste.vianuvem.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class Film {

    private String title;
    private String director;
    private LocalDateTime created;
    private String producer;
    private String url;
    private Set<Starship> starshipList;

}
