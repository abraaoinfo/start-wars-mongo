package com.teste.vianuvem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "starship")
public class Starship {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne( fetch = FetchType.LAZY)
    private Film film;
    private String model;
    private String manufacturer;
    private String uri;
    private LocalDateTime created;


}
