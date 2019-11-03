package com.teste.vianuvem.mapper;

import com.teste.vianuvem.dto.FilmDTO;
import com.teste.vianuvem.model.Film;
import com.teste.vianuvem.model.Planet;
import org.mapstruct.factory.Mappers;

public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);


    Film filmDtoToFilm(FilmDTO dto);

    FilmDTO filmToFilmDto(Planet entity);
}
