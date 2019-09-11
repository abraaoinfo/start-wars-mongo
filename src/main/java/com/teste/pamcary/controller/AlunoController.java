package com.teste.pamcary.controller;


import com.teste.pamcary.dto.AlunoDTO;
import com.teste.pamcary.model.Aluno;
import com.teste.pamcary.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api (value = "api Alunos")
@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @ApiOperation(value = "tras todos os alunos cadastrado", response = List.class)
    public ResponseEntity<List<Aluno>> getAlunos() {
        log.info("buscando todos alunos");

        return ResponseEntity.ok(alunoService.getAllAluno());
    }

    @GetMapping({"/{alunoId}"})
    @ApiOperation(value = "recuperar aluno pelo id", response = List.class)
    public ResponseEntity<Aluno> getAluno(@PathVariable Long alunoId) {
         log.info("buscando aluno por id {}", alunoId);
        return ResponseEntity.ok(alunoService.getAlunoById(alunoId));
    }

    @PostMapping
    @ApiOperation(value = "criar aluno")
    public ResponseEntity<?> getAluno(@RequestBody  @Valid AlunoDTO alunoDTO) {
        log.info("criando aluno {}", alunoDTO);
        alunoService.createAluno(alunoDTO);
        return new ResponseEntity<>(  HttpStatus.CREATED);
    }


    @PutMapping("/{alunodId}")
    @ApiOperation(value = "atualiza aluno")
    public ResponseEntity<?> atualizaAluno(@PathVariable Long alunodId, @RequestBody @Valid AlunoDTO alunoDTO) {
        log.info("criando aluno {}", alunoDTO);
        alunoService.updateAluno(alunodId,alunoDTO);
        return ResponseEntity.ok(alunoDTO);
    }


    @DeleteMapping({"/{alunoId}"})
    @ApiOperation(value = "deleta aluno")
    public ResponseEntity<?> deletaAluno(@PathVariable Long alunoId) {
        log.info("deletando aluno  id {}", alunoId);
        alunoService.removeAluno(alunoId);
        return  ResponseEntity.ok(alunoId);
    }


}
