package com.mz.sge.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AproveitamentoRequestDTO {

	@NotNull(message = "o aluno é obrigatório")
	private Long alunoId;

	@NotNull(message = "a disciplina é obrigatória")
	private Long disciplinaId;

	@NotNull(message = "o semetre é obrigatório")
	@Min(value = 1, message = "o semestre mínimo deve ser 1")
	@Max(value = 8, message = "o semestre máximo deve ser 8")
	private Integer semestre;

	@NotNull(message = "a nota é obrigatória")
	@Min(value = 0, message = "A nota mínima deve ser 0")
	@Max(value = 20, message = "A nota máxima deve ser 20")
	private Double nota1;

	@NotNull(message = "a nota é obrigatória")
	@Min(value = 0, message = "A nota mínima deve ser 0")
	@Max(value = 20, message = "A nota máxima deve ser 20")
	private Double nota2;

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public Long getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Long disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

}
