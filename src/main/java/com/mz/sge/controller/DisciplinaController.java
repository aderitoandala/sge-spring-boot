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
import com.mz.sge.service.DisciplinaService;
import java.util.List;
import  com.mz.sge.dto.DisciplinaResponseDTO;
import  com.mz.sge.dto.DisciplinaRequestDTO;
import jakarta.validation.Valid;


@Tag(name = "Disciplinas", description = "Gestão de disciplinas")
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController{

private final DisciplinaService disciplinaService;

public DisciplinaController(DisciplinaService disciplinaService){
this.disciplinaService=disciplinaService;
}


@Operation(summary = "Listar todas as disciplinas")
@GetMapping
public ResponseEntity<List<DisciplinaResponseDTO>> listarTodas(){
return ResponseEntity.ok(disciplinaService.listarTodas());
}


@Operation(summary = "Buscar disciplina por ID")
@ApiResponse(responseCode = "200",description = "Disciplina encontrada")
@ApiResponse(responseCode = "404",description = "Disciplina não encontrada")
@GetMapping("/id/{id}")
public ResponseEntity<DisciplinaResponseDTO> listarPorId(@PathVariable Long id){
return ResponseEntity.ok(disciplinaService.listarPorId(id));
}


@Operation(summary = "Buscar disciplina por NOME")
@ApiResponse(responseCode = "200",description = "Disciplina encontrada")
@ApiResponse(responseCode = "404",description = "Disciplina não encontrada")
@GetMapping("/nome/{nome}")
public ResponseEntity<List<DisciplinaResponseDTO>> listarPorNome(@PathVariable @Valid  String nome){
return ResponseEntity.ok(disciplinaService.listarPorNome(nome));
}


@Operation(summary = "Registar nova disciplina")
@ApiResponse(responseCode = "201",description = "Disciplina Registada")
@ApiResponse(responseCode = "409",description = "Disciplina já existe")
@ApiResponse(responseCode = "400",description = "Dados inválidos")
@PostMapping("/criar")
public ResponseEntity<DisciplinaResponseDTO> criar(@RequestBody @Valid  DisciplinaRequestDTO dto){
DisciplinaResponseDTO alunoCriado=disciplinaService.criar(dto);
return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
}


@Operation(summary = "Actualizar disciplina por ID")
@ApiResponse(responseCode = "200",description = "Disciplina Actualizada")
@ApiResponse(responseCode = "404",description = "Disciplina não encontrada")
@ApiResponse(responseCode = "400",description = "Dados inválidos")
@PutMapping("/actualizar/{id}")
public ResponseEntity<DisciplinaResponseDTO> actualizar(@PathVariable Long id,@RequestBody @Valid  DisciplinaRequestDTO dto){
return ResponseEntity.ok(disciplinaService.actualizar(id,dto));
}


@Operation(summary = "Apagar disciplina por ID")
@ApiResponse(responseCode = "204",description = "Disciplina apagada")
@ApiResponse(responseCode = "404",description = "Disciplina não encontrada")
@ApiResponse(responseCode = "409",description = "Disciplina em uso")
@DeleteMapping("/apagar/{id}")
public ResponseEntity<Void> apagar(@PathVariable Long id){
disciplinaService.apagar(id);
return ResponseEntity.noContent().build();
}



}
