package com.mz.sge.dto;

public class AproveitamentoRequestDTO {
private Long alunoId;
private Long disciplinaId;
private Integer semestre;
private Double nota1;
private Double nota2;

public Long getAlunoId(){
return alunoId;
}

public void setAlunoId(Long alunoId){
this.alunoId=alunoId;
}

public Long getDisciplinaId(){
return disciplinaId;
}

public void setDisciplinaId(Long disciplinaId){
this.disciplinaId= disciplinaId;
}

public Double getNota1(){
return nota1;
}

public void setNota1(Double nota1){
this.nota1=nota1;
}

public Double getNota2(){
return nota2;
}

public void setNota2(Double nota2){
this.nota2=nota2;
}

public Integer getSemestre(){
return semestre;
}

public void setSemestre(Integer semestre){
this.semestre=semestre;
}


}
