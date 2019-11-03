package com.teste.vianuvem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data

@Document(collection ="planet")
public class Planet {

    @Id
    private String id;
    private String name;
    private String terrain;
    private String climate;
    private String url;
    private LocalDateTime created;
    private Set<Film> filmsList;



}
