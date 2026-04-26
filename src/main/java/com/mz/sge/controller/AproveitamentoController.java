package com.mz.sge.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import jakarta.validation.Valid;


@Tag(name = "Aproveitamentos", description = "Gestão de aproveitamentos")
@RestController
@RequestMapping("/aproveitamentos")
public class AproveitamentoController{

private final  AproveitamentoService aproveitamentoService;

public AproveitamentoController(AproveitamentoService aproveitamentoService){
this.aproveitamentoService=aproveitamentoService;
}


@Operation(summary = "Listar todos os aproveitamentos")
@GetMapping
public ResponseEntity<List<AproveitamentoResponseDTO>> listarTodos(){
return ResponseEntity.ok(aproveitamentoService.listarTodos());
}


@Operation(summary = "Buscar aproveitamento por ID")
@ApiResponse(responseCode = "200",description = "Aproveitamento encontrado")
@ApiResponse(responseCode = "404",description = "Aproveitamento não encontrado")
@GetMapping("/id/{id}")
public ResponseEntity<AproveitamentoResponseDTO> listarPorId(@PathVariable Long id){
return ResponseEntity.ok(aproveitamentoService.listarPorId(id));
}


@Operation(summary = "Buscar aproveitamento por aluno e semestre")
@ApiResponse(responseCode = "200",description = "Aproveitamento encontrado")
@ApiResponse(responseCode = "404",description = "Aproveitamento não encontrado")
@GetMapping("/aluno/{alunoId}/semestre/{semestre}")
public ResponseEntity<AproveitamentoResponseDTO> listarPorAlunoESemestre(@PathVariable Long alunoId,@PathVariable Integer semestre){
return ResponseEntity.ok(aproveitamentoService.listarPorAlunoESemestre(alunoId,semestre));
}


@Operation(summary = "Registar novo aproveitamento")
@ApiResponse(responseCode = "201",description = "Aproveitamento Registado")
@ApiResponse(responseCode = "409",description = "Aproveitamento já existe")
@ApiResponse(responseCode = "400",description = "Dados inválidos")
@PostMapping("/criar")
public ResponseEntity<AproveitamentoResponseDTO> criar(@RequestBody @Valid  AproveitamentoRequestDTO dto){
return ResponseEntity.status(HttpStatus.CREATED).body(aproveitamentoService.criar(dto));
}


@Operation(summary = "Actualizar aproveitamento por ID")
@ApiResponse(responseCode = "200",description = "Aproveitamento Actualizado")
@ApiResponse(responseCode = "404",description = "Aproveitamento não encontrado")
@ApiResponse(responseCode = "400",description = "Dados inválidos")
@PutMapping("/actualizar/{id}")
public ResponseEntity<AproveitamentoResponseDTO> actualizar(@PathVariable Long id,@RequestBody @Valid AproveitamentoRequestDTO dto){
return ResponseEntity.ok(aproveitamentoService.actualizar(id,dto));
}

@Operation(summary = "Apagar aproveitamento por ID")
@ApiResponse(responseCode = "204",description = "Aproveitamento apagado")
@ApiResponse(responseCode = "404",description = "Aproveitamento não encontrado")
@ApiResponse(responseCode = "409",description = "Aproveitamento em uso")
@DeleteMapping("/apagar/{id}")
public ResponseEntity<Void> apagar(@PathVariable Long id){
aproveitamentoService.apagar(id);
return ResponseEntity.noContent().build();
}








}
