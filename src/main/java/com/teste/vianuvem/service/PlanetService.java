package com.teste.vianuvem.service;

import com.teste.vianuvem.dto.FilmDTO;
import com.teste.vianuvem.dto.PlanetDTO;
import com.teste.vianuvem.dto.StarshipDTO;
import com.teste.vianuvem.exception.BadRequestException;
import com.teste.vianuvem.exception.CallApiStartWarsException;
import com.teste.vianuvem.exception.PlanetNotFoundException;
import com.teste.vianuvem.mapper.PlanetMapper;
import com.teste.vianuvem.mapper.StarshipMapper;
import com.teste.vianuvem.model.Film;
import com.teste.vianuvem.model.Planet;
import com.teste.vianuvem.model.Starship;
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
import java.util.*;

@Slf4j
@Service
public class PlanetService {


   @Autowired
   private PlanetRepository planetRepository;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private PlanetMapper planetMapper;

   @Autowired
   private StarshipMapper starshipMapper;


   @Value("${app.api.start-wars.uri}")
   private String baseUri;

   @Transactional
   public void savePlanet(Planet planet){
      try {
         planetRepository.save(planet);
      }catch (Exception ex){
         log.error("error_create_planet_{}",ex.getMessage());
         throw new RuntimeException("error_create_planet", ex);
      }

   }

   public void deletePlanet(String id){
      planetRepository.deleteById(id);
   }

   public List<PlanetDTO> getAllPlanet() {
      List<PlanetDTO> planets = new ArrayList<>();
      planetRepository.findAll().forEach(ett -> planets.add(planetMapper.planetToPlanetDto(ett)));
      return planets;
   }

   public PlanetDTO getPlanetById(String planetId) {

      Optional<Planet> planetOptional = planetRepository.findById(planetId);
      Planet planet = planetOptional.orElseThrow(()
              -> new PlanetNotFoundException("planet_not_found"));

      return planetMapper.planetToPlanetDto(planet);
   }

   public PlanetDTO getPlanetByName(String name) {
      Planet byName = planetRepository.findByName(name);
      return planetMapper.planetToPlanetDto(byName);
   }

   private Set<Starship> getStarships( List<String> uris) {

      Set<Starship> starships = new HashSet<>();
      if(! CollectionUtils.isEmpty(uris)) {
         for (String uri : uris) {
            starships.add(searchStarship(uri));
         }
      }

      return starships;
   }

   private Starship searchStarship( String uri) {

      StarshipDTO starshipDTO = callStarshipService(uri);
      Starship starship = starshipMapper.starshipDtoToFilm(starshipDTO);
      return starship;
   }


   private Set<Film> getFilms( List<String> uris)  {

      Set<Film> films = new HashSet<>();
      if (! CollectionUtils.isEmpty(uris)) {
         for (String uri : uris) {
            films.add(verifyExistFilmOrSaver( uri));
         }
      }
      return films;
   }

   private Film verifyExistFilmOrSaver( String uri) {
      FilmDTO filmDTO = callFilmService(uri);
      Film film = FilmDTO.parseToFilmDTO(filmDTO);
      film.setStarshipList(getStarships(filmDTO.getStarships()));
      return film;
   }


   public void createPlanet(String name)  {

      planetIsExist(name);
      PlanetDTO planetDTO = getPlanetStartWars(name);
      Planet planet =  planetMapper.planetDtoToPlanet(planetDTO);
      planet.setFilmsList(getFilms(planetDTO.getFilms()));

      savePlanet(planet);
   }

   private void planetIsExist(String name) {
      PlanetDTO planetExist = getPlanetByName(name);
      if(planetExist != null) {
         throw new BadRequestException("planet_has_already");
      }
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
