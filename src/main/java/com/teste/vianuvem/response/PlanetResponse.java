package com.teste.vianuvem.response;

import com.teste.vianuvem.dto.PlanetDTO;
import lombok.Data;

import java.util.List;

@Data
public class PlanetResponse {

    public List<PlanetDTO> results;
}
