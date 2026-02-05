package com.mz.sge.service;
import com.mz.sge.repository.AproveitamentoRepository;
import com.mz.sge.repository.AlunoRepository;
import com.mz.sge.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;
import com.mz.sge.entity.AproveitamentoEntity;
import com.mz.sge.entity.AlunoEntity;
import com.mz.sge.entity.DisciplinaEntity;
import com.mz.sge.dto.AproveitamentoResponseDTO;
import com.mz.sge.dto.AproveitamentoRequestDTO;
import com.mz.sge.exception.RecursoNaoEncontradoException;
import com.mz.sge.mapper.AproveitamentoMapper;
import java.util.List;


@Service
public class AproveitamentoService{

private final AproveitamentoRepository aproveitamentoRepository;
private final AlunoRepository alunoRepository;
private final DisciplinaRepository disciplinaRepository;


public AproveitamentoService(AproveitamentoRepository aproveitamentoRepository, AlunoRepository alunoRepository,DisciplinaRepository disciplinaRepository){
this.aproveitamentoRepository=aproveitamentoRepository;
this.alunoRepository=alunoRepository;
this.disciplinaRepository=disciplinaRepository;
}

public List<AproveitamentoResponseDTO> listarTodos(){
return aproveitamentoRepository.findAll().stream().map(AproveitamentoMapper::toDTO).toList();
}

public AproveitamentoResponseDTO listarPorId(Long id){
return AproveitamentoMapper.toDTO(aproveitamentoRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Aproveitamento não encontrado")));
}

public AproveitamentoResponseDTO listarPorAlunoESemestre(Long alunoId,Integer semestre){
return AproveitamentoMapper.toDTO( aproveitamentoRepository.findByAlunoIdAndSemestre(alunoId,semestre).orElseThrow(()-> new RecursoNaoEncontradoException("Aproveitamento não encontrado")));
}


public AproveitamentoResponseDTO criar(AproveitamentoRequestDTO dto){
AlunoEntity aluno=alunoRepository.findById(dto.getAlunoId()).orElseThrow(()-> new RecursoNaoEncontradoException("Aluno não encontrado"));
DisciplinaEntity disciplina=disciplinaRepository.findById(dto.getDisciplinaId()).orElseThrow(()-> new RecursoNaoEncontradoException("Disciplina não encontrada"));
AproveitamentoEntity ap=new AproveitamentoEntity();
ap.setAluno(aluno);
ap.setDisciplina(disciplina);
ap.setSemestre(dto.getSemestre());
ap.setNota1(dto.getNota1());
ap.setNota2(dto.getNota2());
return AproveitamentoMapper.toDTO(aproveitamentoRepository.save(ap));
}

public AproveitamentoResponseDTO actualizar(Long id,AproveitamentoRequestDTO dto){
AproveitamentoEntity ap=aproveitamentoRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Aproveitamento não encontrado"));
AlunoEntity aluno=alunoRepository.findById(dto.getAlunoId()).orElseThrow(()-> new RecursoNaoEncontradoException("Aluno não encontrado"));
DisciplinaEntity disciplina=disciplinaRepository.findById(dto.getDisciplinaId()).orElseThrow(()-> new RecursoNaoEncontradoException("Disciplina não encontrada"));

ap.setAluno(aluno);
ap.setDisciplina(disciplina);
ap.setSemestre(dto.getSemestre());
ap.setNota1(dto.getNota1());
ap.setNota2(dto.getNota2());
return AproveitamentoMapper.toDTO(aproveitamentoRepository.save(ap));
}

public void apagar(Long id){
if(!aproveitamentoRepository.existsById(id)){
throw new  RecursoNaoEncontradoException("Aproveitamento não encontrado");
}
aproveitamentoRepository.deleteById(id);
}





}
