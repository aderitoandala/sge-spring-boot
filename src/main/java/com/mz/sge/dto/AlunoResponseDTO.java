package com.mz.sge.dto;
import com.mz.sge.enums.Sexo;

public class AlunoResponseDTO{
private Long id;
private String nome;
private Sexo sexo;
private String email;

public AlunoResponseDTO(Long id,String nome,Sexo sexo,String email){
this.id=id;
this.nome=nome;
this.sexo=sexo;
this.email=email;
}

//getters 
public Long getId(){
return id;
}

public String  getNome(){
  return nome;
 }

public Sexo getSexo(){
return sexo;
}

public String getEmail(){
return email;
}

public void setEmail(String email){
this.email=email;
}

}
