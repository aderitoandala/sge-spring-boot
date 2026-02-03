package com.mz.sge.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mz.sge.entity.AproveitamentoEntity;
import java.util.Optional;

public interface  AproveitamentoRepository extends JpaRepository<AproveitamentoEntity,Long> {

Optional<AproveitamentoEntity> findByAlunoIdAndSemestre(Long alunoId,int semestre);

}
