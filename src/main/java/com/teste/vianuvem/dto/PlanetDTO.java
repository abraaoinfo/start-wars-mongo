package com.teste.vianuvem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PlanetDTO {
    private Long id;
    private String name;
    private String terrain;
    private String climate;
    private String url;
    private LocalDateTime created;
    private List<FilmDTO> filmsList;
    @JsonIgnore
    private List<String> films;


}
