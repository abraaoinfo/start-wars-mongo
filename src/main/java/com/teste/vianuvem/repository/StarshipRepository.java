package com.teste.vianuvem.repository;

import com.teste.vianuvem.model.Starship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipRepository  extends JpaRepository<Starship, Long> {
    Starship findByUrl(String url);
}
