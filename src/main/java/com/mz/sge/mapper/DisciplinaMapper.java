package com.mz.sge.mapper;
import com.mz.sge.entity.DisciplinaEntity;
import com.mz.sge.dto.DisciplinaRequestDTO;
import com.mz.sge.dto.DisciplinaResponseDTO;

public class DisciplinaMapper{

private DisciplinaMapper(){}

public static DisciplinaEntity toEntity(DisciplinaRequestDTO dto){
DisciplinaEntity d= new DisciplinaEntity();
d.setNome(dto.getNome());
d.setDescricao(dto.getDescricao());
return d;
}

public static DisciplinaResponseDTO toDTO(DisciplinaEntity d){
return
new DisciplinaResponseDTO(d.getId(),d.getNome(),d.getDescricao());
}




}
