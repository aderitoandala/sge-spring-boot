package com.mz.sge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mz.sge.dto.AlunoRequestDTO;
import com.mz.sge.dto.AlunoResponseDTO;
import com.mz.sge.entity.AlunoEntity;
import com.mz.sge.exception.RecursoNaoEncontradoException;
import com.mz.sge.exception.ViolacaoIntegridadeException;
import com.mz.sge.mapper.AlunoMapper;
import com.mz.sge.repository.AlunoRepository;
import com.mz.sge.repository.AproveitamentoRepository;

@Service
public class AlunoService {

	private final AlunoRepository alunoRepository;
	private final AproveitamentoRepository aproveitamentoRepository;

	public AlunoService(AlunoRepository alunoRepository, AproveitamentoRepository aproveitamentoRepository) {
		this.alunoRepository = alunoRepository;
		this.aproveitamentoRepository = aproveitamentoRepository;
	}

	public List<AlunoResponseDTO> listarTodos() {
		return alunoRepository.findAll().stream().map(AlunoMapper::toDTO).toList();
	}

	public AlunoResponseDTO listarPorId(Long id) {
		AlunoEntity aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado"));

		return AlunoMapper.toDTO(aluno);
	}

	public List<AlunoResponseDTO> listarPorNome(String nome) {
		List<AlunoResponseDTO> alunos = alunoRepository.findByNome(nome).stream().map(AlunoMapper::toDTO).toList();
		if (alunos.isEmpty()) {
			throw new RecursoNaoEncontradoException("Aluno não encontrado");
		}
		return alunos;
	}

	public AlunoResponseDTO criar(AlunoRequestDTO dto) {
		if (alunoRepository.existsByEmail(dto.getEmail())) {
			throw new ViolacaoIntegridadeException("Já existe um aluno com o email:" + dto.getEmail());
		}
		AlunoEntity novoAluno = AlunoMapper.toEntity(dto);
		AlunoEntity alunoSalvo = alunoRepository.save(novoAluno);
		return AlunoMapper.toDTO(alunoSalvo);
	}

	public AlunoResponseDTO actualizar(Long id, AlunoRequestDTO dto) {
		if (alunoRepository.existsByEmail(dto.getEmail())) {
			throw new ViolacaoIntegridadeException("Já existe um aluno com o email:" + dto.getEmail());
		}
		AlunoEntity alunoActualizado = alunoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado"));
		alunoActualizado.setNome(dto.getNome());
		alunoActualizado.setSexo(dto.getSexo());
		alunoActualizado.setEmail(dto.getEmail());
		AlunoEntity salvo = alunoRepository.save(alunoActualizado);
		return AlunoMapper.toDTO(salvo);
	}

	public void apagar(Long id) {
		if (aproveitamentoRepository.existsByAlunoId(id)) {
			throw new ViolacaoIntegridadeException("Não é possível apagar esse registro");
		}
		if (!alunoRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Aluno não existente");
		}
		alunoRepository.deleteById(id);
	}

}
