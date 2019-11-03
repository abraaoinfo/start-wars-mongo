package com.teste.vianuvem.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "planet_film",
            joinColumns = { @JoinColumn(name = "planet_id") },
            inverseJoinColumns = { @JoinColumn(name = "film_id") })
    private Set<Film> filmsList  = new HashSet<>();;



}
