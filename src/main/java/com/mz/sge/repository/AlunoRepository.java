package com.mz.sge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mz.sge.entity.AlunoEntity;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
	List<AlunoEntity> findByNome(String nome);

	boolean existsByEmail(String email);

}
