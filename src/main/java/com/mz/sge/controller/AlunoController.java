package com.mz.sge.controller;
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
import com.mz.sge.service.AlunoService;
//import lombok.AllArgsConstructor;
import java.util.List;
import  com.mz.sge.dto.AlunoResponseDTO;
import  com.mz.sge.dto.AlunoRequestDTO;
import jakarta.validation.Valid;
//import com.mz.sge.mapper.AlunoMapper;

//@AllArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController{

private final  AlunoService alunoService;

public AlunoController(AlunoService alunoService){

this.alunoService=alunoService;
}

@GetMapping
public ResponseEntity<List<AlunoResponseDTO>> listarTodos(){
List<AlunoResponseDTO>alunos=alunoService.listarTodos();
return ResponseEntity.ok(alunos);
}

@GetMapping("/id/{id}")
public ResponseEntity<AlunoResponseDTO> listarPorId(@PathVariable Long id){
return ResponseEntity.ok(alunoService.listarPorId(id));
}


@GetMapping("/nome/{nome}")
public ResponseEntity<List<AlunoResponseDTO>> listarPorNome(@PathVariable String nome){
List<AlunoResponseDTO> aluno=alunoService.listarPorNome(nome);
return ResponseEntity.ok(aluno);
}


@PostMapping("/criar")
public ResponseEntity <AlunoResponseDTO>  criar(@RequestBody @Valid AlunoRequestDTO dto){
AlunoResponseDTO alunoCriado=alunoService.criar(dto);
return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
}


@PutMapping("/actualizar/id/{id}")
public ResponseEntity <AlunoResponseDTO>  actualizar(@PathVariable Long id,@RequestBody @Valid  AlunoRequestDTO  dto){
AlunoResponseDTO alunoActualizado=alunoService.actualizar(id,dto);
return ResponseEntity.ok(alunoActualizado);
}


@DeleteMapping("/apagar/{id}")
public ResponseEntity<Void>apagar(@PathVariable Long id){
alunoService.apagar(id);
return ResponseEntity.noContent().build();

}








}
