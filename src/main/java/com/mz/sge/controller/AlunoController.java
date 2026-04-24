package com.mz.sge.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.List;
import  com.mz.sge.dto.AlunoResponseDTO;
import  com.mz.sge.dto.AlunoRequestDTO;
import jakarta.validation.Valid;

@Tag(name = "Alunos", description = "Gestão de alunos")
@RestController
@RequestMapping("/alunos")
public class AlunoController{

private final  AlunoService alunoService;

public AlunoController(AlunoService alunoService){

this.alunoService=alunoService;
}

@Operation(summary = "Listar todos os alunos")
@GetMapping
public ResponseEntity<List<AlunoResponseDTO>> listarTodos(){
List<AlunoResponseDTO>alunos=alunoService.listarTodos();
return ResponseEntity.ok(alunos);
}

@Operation(summary = "Buscar aluno por ID")
@ApiResponse(responseCode = "200",description = "Aluno encontrado")
@ApiResponse(responseCode = "404",description = "Aluno não encontrado")
@GetMapping("/id/{id}")
public ResponseEntity<AlunoResponseDTO> listarPorId(@PathVariable Long id){
return ResponseEntity.ok(alunoService.listarPorId(id));
}

@Operation(summary = "Buscar aluno por NOME")
@ApiResponse(responseCode = "200",description = "Aluno encontrado")
@ApiResponse(responseCode = "404",description = "Aluno não encontrado")
@GetMapping("/nome/{nome}")
public ResponseEntity<List<AlunoResponseDTO>> listarPorNome(@PathVariable String nome){
List<AlunoResponseDTO> aluno=alunoService.listarPorNome(nome);
return ResponseEntity.ok(aluno);
}

@Operation(summary = "Registar novo aluno")
@ApiResponse(responseCode = "201",description = "Aluno Registado")
@ApiResponse(responseCode = "409",description = "Aluno já existe")
@ApiResponse(responseCode = "400",description = "Dados inválidos")
@PostMapping("/criar")
public ResponseEntity <AlunoResponseDTO>  criar(@RequestBody @Valid AlunoRequestDTO dto){
AlunoResponseDTO alunoCriado=alunoService.criar(dto);
return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
}

@Operation(summary = "Actualizar aluno por ID")
@ApiResponse(responseCode = "200",description = "Aluno actualizado")
@ApiResponse(responseCode = "404",description = "Aluno não encontrado")
@ApiResponse(responseCode = "400",description = "Dados inválidos")
@PutMapping("/actualizar/{id}")
public ResponseEntity <AlunoResponseDTO>  actualizar(@PathVariable Long id,@RequestBody @Valid  AlunoRequestDTO  dto){
AlunoResponseDTO alunoActualizado=alunoService.actualizar(id,dto);
return ResponseEntity.ok(alunoActualizado);
}

@Operation(summary = "Apagar aluno por ID")
@ApiResponse(responseCode = "204",description = "Aluno apagado")
@ApiResponse(responseCode = "404",description = "Aluno não encontrado")
@ApiResponse(responseCode = "409",description = "Aluno em uso")
@DeleteMapping("/apagar/{id}")
public ResponseEntity<Void>apagar(@PathVariable Long id){
alunoService.apagar(id);
return ResponseEntity.noContent().build();

}








}
