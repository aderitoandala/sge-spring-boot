package com.mz.sge.mapper;
import com.mz.sge.dto.AlunoRequestDTO;
import com.mz.sge.dto.AlunoResponseDTO;
import com.mz.sge.entity.AlunoEntity;

public class AlunoMapper{

public static AlunoEntity toEntity(AlunoRequestDTO dto){
AlunoEntity aluno =new AlunoEntity();
aluno.setNome(dto.getNome());
aluno.setSexo(dto.getSexo());
aluno.setEmail(dto.getEmail());
return aluno;
}

public static AlunoResponseDTO toDTO(AlunoEntity aluno){
return 
new AlunoResponseDTO(aluno.getId(),aluno.getNome(),aluno.getSexo(),aluno.getEmail());

}

}
