
 package com.mz.sge.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
  import com.mz.sge.entity.DisciplinaEntity;
  import java.util.List;

  public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity,Long>{

 List< DisciplinaEntity> findByNome(String nome);
  DisciplinaEntity deleteByNome(String nome);

 }
