package com.mz.sge.dto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DisciplinaResponseDTO{
  private Long id;
  private String nome;
  private String descricao;


 public Long getId(){
  return id;
 }

  public String getNome(){
  return nome;
 }

 public String getDescricao(){
   return descricao;
 }

 


}
