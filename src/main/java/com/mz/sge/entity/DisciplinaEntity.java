
  package com.mz.sge.entity;
  import jakarta.persistence.Entity;
  import jakarta.persistence.Table;
  import jakarta.persistence.Column;
  import jakarta.persistence.Id;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import lombok.NoArgsConstructor;
  import lombok.AccessLevel;
 import lombok.AllArgsConstructor;
 import java.io.Serializable;
 
 //@Getter
 //@Setter
 @AllArgsConstructor
 @NoArgsConstructor(access = AccessLevel.PUBLIC)
 @Entity
 @Table(name ="DISCIPLINA")
 public class DisciplinaEntity implements Serializable {

 private static final long serialVersionUID=1L;

 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)
 private Long id;

 @Column(nullable=false,length=20,unique=true)
 private String nome;

@Column(length=100)
 private String descricao;

 public String getDescricao() {
      return this.descricao;
 }

 public void setDescricao(String descricao) {
      this.descricao=descricao;
 }

 public Long getId() {
     return this.id;
 }

 public String getNome(){
 return this.nome;
 }

 public void setNome(String nome){
 this.nome=nome;
 }


}
