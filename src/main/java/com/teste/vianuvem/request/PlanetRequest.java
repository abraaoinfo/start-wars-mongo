package com.teste.vianuvem.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanetRequest {

    @NotBlank(message = "name_not_null")
    private String name;
}
