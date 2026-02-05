package com.mz.sge.mapper;
import com.mz.sge.entity.AproveitamentoEntity;
import com.mz.sge.dto.AproveitamentoResponseDTO;
import com.mz.sge.dto.AproveitamentoRequestDTO;
import com.mz.sge.enums.Situacao;
import com.mz.sge.entity.AlunoEntity;
import com.mz.sge.entity.DisciplinaEntity;

public class AproveitamentoMapper{


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
