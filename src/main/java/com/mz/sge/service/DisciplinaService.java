  package com.mz.sge.service;
  import com.mz.sge.repository.DisciplinaRepository;
  import org.springframework.stereotype.Service;
   import com.mz.sge.entity.DisciplinaEntity;
  import java.util.List;
  import com.mz.sge.exception.RecursoNaoEncontradoException;
  import com.mz.sge.dto.DisciplinaResponseDTO;
  import com.mz.sge.dto.DisciplinaRequestDTO;
 import com.mz.sge.mapper.DisciplinaMapper;
  //import lombok.AllArgsConstructor;


  @Service
  public class DisciplinaService{

  private final DisciplinaRepository disciplinaRepository;

  public DisciplinaService(DisciplinaRepository disciplinaRepository){
  this.disciplinaRepository=disciplinaRepository;
}

public List<DisciplinaResponseDTO> listarTodas(){
return disciplinaRepository.findAll().stream().map(DisciplinaMapper::toDTO).toList();
}

public DisciplinaResponseDTO  listarPorId(Long id){
 return DisciplinaMapper.toDTO( disciplinaRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Disciplina não encontrada com id"+" "+id)));
}

public List<DisciplinaResponseDTO> listarPorNome(String nome){
 return disciplinaRepository.findByNome(nome).stream().map(DisciplinaMapper::toDTO).toList();
}

public DisciplinaResponseDTO criar(DisciplinaRequestDTO dto){
  return  DisciplinaMapper.toDTO( disciplinaRepository.save(DisciplinaMapper.toEntity(dto)));
}

public DisciplinaResponseDTO actualizar(Long id,DisciplinaRequestDTO dto){
DisciplinaEntity d= disciplinaRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Disciplina não encontrada"));
d.setNome(dto.getNome());
d.setDescricao(dto.getDescricao());
  return DisciplinaMapper.toDTO(disciplinaRepository.save(d));
}

public void apagar(Long id){
if(!disciplinaRepository.existsById(id)){
throw new RecursoNaoEncontradoException("Disciplina não encontrada com id"+" "+id); 
}
disciplinaRepository.deleteById(id);
}




}
