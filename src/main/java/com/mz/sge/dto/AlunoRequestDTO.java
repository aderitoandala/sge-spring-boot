package com.mz.sge.dto;
//import lombok.Data;
import com.mz.sge.enums.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

public class AlunoRequestDTO{

@NotNull(message="Nome não pode ser nulo")
@NotBlank(message="O nome deve ser explícito")
private String nome;

private Sexo sexo;

@Email(message="Email inválido")
private String email;

public String getNome() {
     return nome;
}


public void setNome(String nome) {
     this.nome=nome;
}


public Sexo getSexo(){
return sexo;
}

public void setSexo(Sexo sexo){
this.sexo=sexo;
}

public String getEmail(){
return email;
}

public void setEmail(String email){
this.email= email;
}

}
