package com.mz.sge.service;
import com.mz.sge.repository.AproveitamentoRepository;
import org.springframework.stereotype.Service;
import com.mz.sge.entity.AproveitamentoEntity;
import com.mz.sge.dto.AproveitamentoResponseDTO;
import com.mz.sge.dto.AproveitamentoRequestDTO;
import com.mz.sge.exception.RecursoNaoEncontradoException;
import com.mz.sge.mapper.AproveitamentoMapper;
import java.util.List;

@Service
public class AproveitamentoService{

private final AproveitamentoRepository aproveitamentoRepository;

public AproveitamentoService(AproveitamentoRepository aproveitamentoRepository){
this.aproveitamentoRepository=aproveitamentoRepository;
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
return AproveitamentoMapper.toDTO(aproveitamentoRepository.save(AproveitamentoMapper.toEntity(dto)));
}

public AproveitamentoResponseDTO actualizar(Long id,AproveitamentoRequestDTO dto){
AproveitamentoEntity ap=aproveitamentoRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Aproveitamento não encontrado"));
ap.getAluno().setId(dto.getAlunoId());
ap.getDisciplina().setId(dto.getDisciplinaId());
ap.setSemestre(dto.getSemestre());
ap.setNota1(dto.getNota1());
ap.setNota2(dto.getNota2());
return AproveitamentoMapper.toDTO(ap);
}

public void apagar(Long id){
if(!aproveitamentoRepository.existsById(id)){
throw new  RecursoNaoEncontradoException("Aproveitamento não encontrado");
}
aproveitamentoRepository.deleteById(id);
}





}
