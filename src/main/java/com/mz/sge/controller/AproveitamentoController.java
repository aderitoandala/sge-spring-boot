package com.mz.sge.controller;
import com.mz.sge.service.AproveitamentoService;
import com.mz.sge.dto.AproveitamentoResponseDTO;
import com.mz.sge.dto.AproveitamentoRequestDTO;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/aproveitamentos")
public class AproveitamentoController{

private final  AproveitamentoService aproveitamentoService;

public AproveitamentoController(AproveitamentoService aproveitamentoService){
this.aproveitamentoService=aproveitamentoService;
}

@GetMapping
public ResponseEntity<List<AproveitamentoResponseDTO>> listarTodos(){
return ResponseEntity.ok(aproveitamentoService.listarTodos());
}

@GetMapping("/id/{id}")
public ResponseEntity<AproveitamentoResponseDTO> listarPorId(@PathVariable Long id){
return ResponseEntity.ok(aproveitamentoService.listarPorId(id));
}

@GetMapping("/aluno/{alunoId}/semestre/{semestre}")
public ResponseEntity<AproveitamentoResponseDTO> listarPorAlunoESemestre(@PathVariable Long alunoId,@PathVariable Integer semestre){
return ResponseEntity.ok(aproveitamentoService.listarPorAlunoESemestre(alunoId,semestre));
}

@PostMapping("/criar")
public ResponseEntity<AproveitamentoResponseDTO> criar(@RequestBody AproveitamentoRequestDTO dto){
return ResponseEntity.status(HttpStatus.CREATED).body(aproveitamentoService.criar(dto));
}

@PutMapping("/actualizar/{id}")
public ResponseEntity<AproveitamentoResponseDTO> actualizar(@PathVariable Long id,@RequestBody AproveitamentoRequestDTO dto){
return ResponseEntity.ok(aproveitamentoService.actualizar(id,dto));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> apagar(@PathVariable Long id){
aproveitamentoService.apagar(id);
return ResponseEntity.noContent().build();
}










}
