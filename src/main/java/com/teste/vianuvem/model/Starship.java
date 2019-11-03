package com.teste.vianuvem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="starship")
public class Starship {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private String manufacturer;
    @Column(unique = true)
    private String url;
    private LocalDateTime created;


}
