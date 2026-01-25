package com.mz.sge.service;
import com.mz.sge.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import com.mz.sge.entity.AlunoEntity;
//import lombok.AllArgsConstructor;
import java.util.List;
import com.mz.sge.exception.RecursoNaoEncontradoException;
import com.mz.sge.dto.AlunoResponseDTO;
import com.mz.sge.dto.AlunoRequestDTO;
import com.mz.sge.mapper.AlunoMapper;

@Service
public class AlunoService{

private final AlunoRepository alunoRepository;

public AlunoService(AlunoRepository alunoRepository){
this.alunoRepository=alunoRepository;
}

public List<AlunoResponseDTO>listarTodos(){
return alunoRepository.findAll().stream().map(AlunoMapper::toDTO).toList();
}

public AlunoResponseDTO listarPorId(Long id){
AlunoEntity aluno = alunoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado"));

return AlunoMapper.toDTO(aluno);
}

public List<AlunoResponseDTO> listarPorNome(String nome){
List<AlunoResponseDTO>alunos= alunoRepository.findByNome(nome).stream().map(AlunoMapper::toDTO).toList();
if(alunos.isEmpty()){
throw new RecursoNaoEncontradoException("Aluno não encontrado");
}
return alunos;
}

public AlunoResponseDTO criar(AlunoRequestDTO dto){
AlunoEntity novoAluno = AlunoMapper.toEntity(dto);
AlunoEntity alunoSalvo= alunoRepository.save(novoAluno);
return AlunoMapper.toDTO(alunoSalvo);
}

public AlunoResponseDTO actualizar(Long id,AlunoRequestDTO dto){
AlunoEntity alunoActualizado= alunoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado"));
alunoActualizado.setNome(dto.getNome());
alunoActualizado.setSexo(dto.getSexo());
alunoActualizado.setEmail(dto.getEmail());
AlunoEntity salvo= alunoRepository.save(alunoActualizado);
return AlunoMapper.toDTO(salvo);
}

public void apagar(Long id){
if(!alunoRepository.existsById(id)){
throw new RecursoNaoEncontradoException("Aluno não existente");
}
 alunoRepository.deleteById(id);
}










}
