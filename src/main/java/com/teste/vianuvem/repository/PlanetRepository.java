package com.teste.vianuvem.repository;

import com.teste.vianuvem.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {


    Planet findByName(String name);


}
