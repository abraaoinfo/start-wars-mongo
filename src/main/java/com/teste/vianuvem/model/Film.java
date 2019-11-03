package com.teste.vianuvem.model;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long episodeId;
    private String title;
    private String director;
    private LocalDateTime created;
    private String producer;
    private String url;
   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "film",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Starship> starshipList;*/

}
