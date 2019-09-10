package com.teste.pamcary.service;

import com.teste.pamcary.dto.AlunoDTO;
import com.teste.pamcary.exception.AlunoNotFoundException;
import com.teste.pamcary.model.Aluno;
import com.teste.pamcary.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlunoService {


   @Autowired
   private AlunoRepository alunoRepository;

   public void createAluno(AlunoDTO alunoDTO){
      Aluno aluno = parseAlunoDtoToAluno(alunoDTO, new Aluno());
      alunoRepository.save(aluno);
   }

   @Transactional
   public void removeAluno(Long id){
      alunoRepository.deleteById(id);
   }

   public List<Aluno> getAllAluno() {
      return alunoRepository.findAll();
   }

   public Aluno getAlunoById(Long alunodId) {
      Optional<Aluno> aluno = alunoRepository.findById(alunodId);
      return aluno.orElseThrow(() ->new AlunoNotFoundException("aluno_not_found"));
   }

   public Aluno updateAluno(Long alunodId, AlunoDTO alunoDTO) {
      Aluno aluno = getAlunoById(alunodId);
      aluno = parseAlunoDtoToAluno(alunoDTO, aluno);
      return alunoRepository.save(aluno);
   }

   private Aluno parseAlunoDtoToAluno(AlunoDTO alunoDTO, Aluno aluno){
      BeanUtils.copyProperties(alunoDTO, aluno);
      return aluno;
   }

}
