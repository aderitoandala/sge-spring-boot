package com.mz.sge.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DisciplinaRequestDTO{

@NotNull
@NotBlank
@Size(max=20)
private String nome;
private String descricao;

public String getNome(){
return nome;
}

public String getDescricao(){
  return descricao;
}

public void setNome(String nome){
  this.nome=nome;
}

public void setDescricao(String descricao){
   this.descricao=descricao;
}


}
