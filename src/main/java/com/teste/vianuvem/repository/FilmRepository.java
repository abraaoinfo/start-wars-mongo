package com.teste.vianuvem.repository;

import com.teste.vianuvem.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {

    Film findByUrl(String url);
}
