package com.teste.vianuvem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PlanetDTO {

    private String name;
    private String terrain;
    private String climate;
    private LocalDateTime created;
    private List<FilmDTO> filmsList;
    private List<String> films;


}
