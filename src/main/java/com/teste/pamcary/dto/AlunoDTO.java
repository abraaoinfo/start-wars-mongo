package com.teste.pamcary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AlunoDTO {

    @NotNull(message="idade do aluno  não pode ser null")
    @Min(value=1, message = "idade do aluno não pode ser zero")
    private Integer idade;

    @NotBlank(message="nome do aluno não pode ser null")
    private String nome;

}
