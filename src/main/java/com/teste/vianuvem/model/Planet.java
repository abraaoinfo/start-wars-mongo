package com.teste.vianuvem.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "planet")
public class Planet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String terrain;
    private String climate;
    private String url;
    private LocalDateTime created;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "planet_film",
            joinColumns = { @JoinColumn(name = "planet_id") },
            inverseJoinColumns = { @JoinColumn(name = "film_id") })
    private List<Film> filmsList;



}
