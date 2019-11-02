package com.teste.vianuvem.service;

import com.teste.vianuvem.dto.FilmDTO;
import com.teste.vianuvem.dto.PlanetDTO;
import com.teste.vianuvem.dto.StarshipDTO;
import com.teste.vianuvem.exception.BadRequestException;
import com.teste.vianuvem.exception.CallApiStartWarsException;
import com.teste.vianuvem.exception.PlanetNotFoundException;
import com.teste.vianuvem.mapper.PlanetMapper;
import com.teste.vianuvem.model.Planet;
import com.teste.vianuvem.repository.FilmRepository;
import com.teste.vianuvem.repository.PlanetRepository;
import com.teste.vianuvem.response.PlanetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlanetService {


   @Autowired
   private PlanetRepository planetRepository;

   @Autowired
   private FilmRepository filmRepository;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private PlanetMapper planetMapper;


   @Value("${app.api.start-wars.uri}")
   private String baseUri;

   @Transactional
   public void savePlanet(Planet planet){
      try {
         planetRepository.saveAndFlush(planet);
      }catch (Exception ex){
         log.error("error_create_planet_{}",ex.getMessage());
         throw new RuntimeException("error_create_planet", ex);
      }

   }

   public void deletePlanet(Long id){
      planetRepository.deleteById(id);
   }

   public List<Planet> getAllPlanet() {
      return planetRepository.findAll();
   }

   public Planet getPlanetById(Long planetId) {
      Optional<Planet> planet = planetRepository.findById(planetId);
      return planet.orElseThrow(() ->new PlanetNotFoundException("planet_not_found"));
   }

   public Planet getPlanetByName(String name) {
      return planetRepository.findByName(name);

   }

   private List<StarshipDTO> getStarships( List<String> uris) {

      List<StarshipDTO> starships = new ArrayList<>();
      if(! CollectionUtils.isEmpty(uris)) {
         for (String uri : uris) {
            starships.add(callStarshipService(uri));
         }
      }

      return starships;
   }

   private List<FilmDTO> getFilms( List<String> uris)  {


      List<FilmDTO> films = new ArrayList<>();
      if (! CollectionUtils.isEmpty(uris)) {
         for (String uri : uris) {
            films.add(searchFilms( uri));
         }
      }
      return films;
   }

   private FilmDTO searchFilms( String uri) {

      FilmDTO filmDTO = FilmDTO.parseToFilmDTO(filmRepository.findByUrl(uri));

      if(filmDTO == null) {
         filmDTO = callFilmService(uri);
         filmDTO.setStarshipList(getStarships(filmDTO.getStarships()));
      }

      return filmDTO;
   }


   public PlanetDTO createPlanet(String name)  {

      planetIsExist(name);
      PlanetDTO planetDTO = getPlanetStartWars(name);
      planetDTO.setFilmsList(getFilms(planetDTO.getFilms()));
      Planet planet = parsePlanetDtoToPlanet(planetDTO);
      savePlanet(planet);
      return planetDTO;

   }

   private void planetIsExist(String name) {
      Planet planetExist = getPlanetByName(name);
      if(planetExist != null) {
         throw new BadRequestException("planet_has_already");
      }
   }


   private Planet parsePlanetDtoToPlanet(PlanetDTO planetDTO){

      Planet planet = planetMapper.planetDtoToPlanet(planetDTO);

      return planet;
   }



   private URI getUriSearchName(String name) {
      return UriComponentsBuilder
              .fromHttpUrl(baseUri)
              .path("planets")
              .queryParam("search", name)
              .build().toUri();
   }

   private PlanetDTO getPlanetStartWars(String name) {
      PlanetResponse planetResponse;
      try {
         planetResponse = restTemplate.getForObject(getUriSearchName(name), PlanetResponse.class);
      }catch (Exception ex){
         log.error("error_searching_film {}", ex.getMessage());
         throw new CallApiStartWarsException("error_searching_film", ex);
      }

      if(planetResponse == null || CollectionUtils.isEmpty(planetResponse.getResults())){
         throw new BadRequestException("planet_not_exits_api_start_wars") ;
      }

      return planetResponse.getResults().get(0);
   }

   private FilmDTO callFilmService(String uri) {
      try{
         return  restTemplate.getForObject(uri, FilmDTO.class);
      }catch (Exception ex){
         log.error("error_searching_film_{}", ex.getMessage());
         throw new CallApiStartWarsException("error_searching_film", ex);
      }

   }


   private StarshipDTO callStarshipService(String uri) {
      try {
         return restTemplate.getForObject(uri, StarshipDTO.class);
      }catch (Exception ex){
         log.error("error_searching_starship_{}", ex.getMessage());
         throw new CallApiStartWarsException("error_searching_starship", ex);
      }

   }


}
