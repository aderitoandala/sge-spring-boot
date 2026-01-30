package com.mz.sge.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
  import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.mz.sge.enums.Sexo;
import java.util.List;


//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name ="ALUNO")
public class AlunoEntity implements Serializable {

private static final long serialVersionUID=1L;

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;

@Column(nullable=false,length=20)
private String nome;

@Column(length=100,unique=true)
private String email;

@Enumerated(EnumType.STRING)
@Column(nullable=false)
private Sexo sexo;

@OneToMany(mappedBy= "aluno")
private List<AproveitamentoEntity> aproveitamentos;

public Sexo getSexo() {
     return this.sexo;
}

public void setSexo(Sexo sexo) {
     this.sexo=sexo;
}

public Long getId() {
    return this.id;
}

public String getNome(){
return this.nome;
}

public String getEmail(){
return this.email;
}

public void setNome(String nome){
this.nome=nome;
}


public void setEmail(String email){
this.email=email;
}

public List<AproveitamentoEntity> getAproveitamentos(){
return aproveitamentos;
}

public void  setAproveitamentos(List<AproveitamentoEntity> aproveitamentos){
 this.aproveitamentos=aproveitamentos;
}






}
