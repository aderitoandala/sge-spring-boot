package com.mz.sge.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DisciplinaRequestDTO{

@NotNull(message="o nome é obrigatório")
@NotBlank(message="o nome não pode estar em branco")
@Size(max=20,message="o nome deve ter até no máximo 20 caracteres ")
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
