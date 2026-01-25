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
import com.mz.sge.service.DisciplinaService;
import java.util.List;
import  com.mz.sge.dto.DisciplinaResponseDTO;
 import  com.mz.sge.dto.DisciplinaRequestDTO;
import jakarta.validation.Valid;
//import com.mz.sge.mapper.AlunoMapper;
//import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController{

private final DisciplinaService disciplinaService;

public DisciplinaController(DisciplinaService disciplinaService){
this.disciplinaService=disciplinaService;
}

@GetMapping
public ResponseEntity<List<DisciplinaResponseDTO>> listarTodas(){
return ResponseEntity.ok(disciplinaService.listarTodas());
}

@GetMapping("/id/{id}")
public ResponseEntity<DisciplinaResponseDTO> listarPorId(@PathVariable Long id){
return ResponseEntity.ok(disciplinaService.listarPorId(id));
}

@GetMapping("/nome/{nome}")
public ResponseEntity<List<DisciplinaResponseDTO>> listarPorNome(@PathVariable @Valid  String nome){
return ResponseEntity.ok(disciplinaService.listarPorNome(nome));
}

@PostMapping("/criar")
public ResponseEntity<DisciplinaResponseDTO> criar(@RequestBody @Valid  DisciplinaRequestDTO dto){
DisciplinaResponseDTO alunoCriado=disciplinaService.criar(dto);
return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
}

@PutMapping("/actualizar")
public ResponseEntity<DisciplinaResponseDTO> actualizar(@PathVariable Long id,@RequestBody @Valid  DisciplinaRequestDTO dto){
return ResponseEntity.ok(disciplinaService.actualizar(id,dto));
}

@DeleteMapping("/apagar/{id}")
public ResponseEntity<Void> apagar(@PathVariable Long id){
disciplinaService.apagar(id);
return ResponseEntity.noContent().build();
}



}
