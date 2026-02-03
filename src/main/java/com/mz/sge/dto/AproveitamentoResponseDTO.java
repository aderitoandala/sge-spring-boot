package com.mz.sge.dto;
import com.mz.sge.enums.Situacao;

public class AproveitamentoResponseDTO{
private Long id;
private String aluno;
private String disciplina;
private Integer semestre;
private Double nota1;
private Double nota2;
private Double media;
private Situacao situacao;

public AproveitamentoResponseDTO(Long id,String aluno,String disciplina,Integer semestre,Double nota1,Double nota2,Double media,Situacao situacao){
this.id=id;
this.aluno=aluno;
this.disciplina=disciplina;
this.semestre=semestre;
this.nota1=nota1;
this.nota2=nota2;
this.media=media;
this.situacao=situacao;
}


public Long getId(){
return id;
}


public String getAluno(){
return aluno;
}

public String getDisciplina(){
return disciplina;
}

public Integer getSemestre(){
return semestre;
}

public Double getNota1(){
return nota1;
}

public Double getNota2(){
return nota2;
}
public Double getMedia(){
return media;
}

public Situacao getSituacao(){
return situacao;
}







}
