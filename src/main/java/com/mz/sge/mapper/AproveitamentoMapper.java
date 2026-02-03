package com.mz.sge.mapper;
import com.mz.sge.entity.AproveitamentoEntity;
import com.mz.sge.dto.AproveitamentoResponseDTO;
import com.mz.sge.dto.AproveitamentoRequestDTO;
import com.mz.sge.enums.Situacao;

public class AproveitamentoMapper{

public static AproveitamentoEntity toEntity(AproveitamentoRequestDTO dto){
AproveitamentoEntity ap=new AproveitamentoEntity();
ap.getAluno().setId(dto.getAlunoId());
ap.getDisciplina().setId(dto.getDisciplinaId());
ap.setSemestre(dto.getSemestre());
ap.setNota1(dto.getNota1());
ap.setNota2(dto.getNota2());
return ap;
}

public static AproveitamentoResponseDTO toDTO(AproveitamentoEntity ap){
Double media=null;
Situacao situacao=null;
if(ap.getNota1()!=null && ap.getNota2()!=null){
media=(ap.getNota1()+ap.getNota2())/2;
situacao=media<10? Situacao.REPROVADO:Situacao.APROVADO;
}
return new AproveitamentoResponseDTO(ap.getId(),ap.getAluno().getNome(),ap.getDisciplina().getNome(),ap.getSemestre(),ap.getNota1(),ap.getNota2(),media,situacao);
}


}
