package com.mz.sge.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mz.sge.entity.AlunoEntity;
import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoEntity,Long>{
List<AlunoEntity>findByNome(String nome);
}
