package com.teste.vianuvem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@Table(name="film")
public class Film {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private LocalDateTime created;
    private String producer;
    private String url;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "film_startship",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "starship_id") })
    private Set<Starship> starshipList;

}
