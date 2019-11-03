package com.teste.vianuvem.controller;


import com.teste.vianuvem.dto.PlanetDTO;
import com.teste.vianuvem.model.Planet;
import com.teste.vianuvem.request.PlanetRequest;
import com.teste.vianuvem.service.PlanetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api (value = "api start wars")
@RestController
@RequestMapping("/v1/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping
    @ApiOperation(value = "create planet", response = Planet.class,   consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity  createPlanet(@Valid @RequestBody PlanetRequest planetRequest) {
         planetService.createPlanet( planetRequest.getName());
         return  new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    @ApiOperation(value = "get all planet", response = List.class)
    public ResponseEntity<List<PlanetDTO>> getPlanetAll()  {
        return ResponseEntity.ok(planetService.getAllPlanet());

    }

    @DeleteMapping("/{planetId}")
    @ApiOperation(value = "delete planet by id")
    public void deletePlanet(@PathVariable String planetId)  {

        planetService.deletePlanet(planetId);
    }


    @GetMapping("/{planetId}")
    @ApiOperation(value = "get planet by id", response = PlanetDTO.class)
    public ResponseEntity<PlanetDTO> getPlanetById(@PathVariable String planetId)  {
        return ResponseEntity.ok(planetService.getPlanetById(planetId));
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "get planet by name", response = PlanetDTO.class)
    public ResponseEntity<PlanetDTO> getPlanetByName(@RequestParam("name") String name)  {
        return  ResponseEntity.ok(planetService.getPlanetByName(name));
    }




}
