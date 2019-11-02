package com.teste.vianuvem.repository;

import com.teste.vianuvem.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {


    Planet findByName(String name);


}
