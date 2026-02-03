package com.mz.sge.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
  import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.UniqueConstraint;

@NoArgsConstructor(access=AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name="APROVEITAMENTO",uniqueConstraints={@UniqueConstraint(name="uk_aluno_disciplina_semestre",columnNames={"aluno_id","disciplina_id","semestre"})})
public class AproveitamentoEntity implements Serializable {

public static final long serialVersionUID=1L;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name="aluno_id",nullable=false)
private AlunoEntity aluno;

@ManyToOne
@JoinColumn(name="disciplina_id",nullable=false)
private DisciplinaEntity disciplina;

@Column(nullable=false)
private Integer semestre;

@Column(nullable=false)
private Double nota1;

@Column(nullable=false)
private Double nota2;

public Long getId(){
return id;
}

public void setId(Long id){
this.id=id;
}

public AlunoEntity getAluno(){
return aluno;
}

public void setAluno(AlunoEntity aluno){
this.aluno=aluno;
}

public DisciplinaEntity getDisciplina(){
return disciplina;
}

public void setDisciplina(DisciplinaEntity disciplina){
this.disciplina=disciplina;
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
