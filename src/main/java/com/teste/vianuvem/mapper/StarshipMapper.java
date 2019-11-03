package com.teste.vianuvem.mapper;

import com.teste.vianuvem.dto.StarshipDTO;
import com.teste.vianuvem.model.Starship;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StarshipMapper {

    StarshipMapper INSTANCE = Mappers.getMapper(StarshipMapper.class);


    Starship starshipDtoToFilm(StarshipDTO dto);

    StarshipDTO filmToFilmDto(Starship entity);
}
