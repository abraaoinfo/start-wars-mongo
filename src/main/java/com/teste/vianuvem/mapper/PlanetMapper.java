package com.teste.vianuvem.mapper;


import com.teste.vianuvem.dto.PlanetDTO;
import com.teste.vianuvem.model.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlanetMapper {

	PlanetMapper INSTANCE = Mappers.getMapper(PlanetMapper.class);


	Planet planetDtoToPlanet(PlanetDTO dto);

	PlanetDTO planetToPlanetDto(Planet entity);

}
