package com.mz.sge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mz.sge.entity.DisciplinaEntity;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {

	List<DisciplinaEntity> findByNome(String nome);

	boolean existsByNome(String nome);

}
