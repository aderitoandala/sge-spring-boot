package com.mz.sge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mz.sge.dto.DisciplinaRequestDTO;
import com.mz.sge.dto.DisciplinaResponseDTO;
import com.mz.sge.entity.DisciplinaEntity;
import com.mz.sge.exception.RecursoNaoEncontradoException;
import com.mz.sge.exception.ViolacaoIntegridadeException;
import com.mz.sge.mapper.DisciplinaMapper;
import com.mz.sge.repository.AproveitamentoRepository;
import com.mz.sge.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	private final DisciplinaRepository disciplinaRepository;
	private final AproveitamentoRepository aproveitamentoRepository;

	public DisciplinaService(DisciplinaRepository disciplinaRepository,
			AproveitamentoRepository aproveitamentoRepository) {
		this.disciplinaRepository = disciplinaRepository;
		this.aproveitamentoRepository = aproveitamentoRepository;
	}

	public List<DisciplinaResponseDTO> listarTodas() {
		return disciplinaRepository.findAll().stream().map(DisciplinaMapper::toDTO).toList();
	}

	public DisciplinaResponseDTO listarPorId(Long id) {
		return DisciplinaMapper.toDTO(disciplinaRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Disciplina não encontrada com id" + " " + id)));
	}

	public List<DisciplinaResponseDTO> listarPorNome(String nome) {
		return disciplinaRepository.findByNome(nome).stream().map(DisciplinaMapper::toDTO).toList();
	}

	public DisciplinaResponseDTO criar(DisciplinaRequestDTO dto) {
		if (disciplinaRepository.existsByNome(dto.getNome())) {
			throw new ViolacaoIntegridadeException("Já existe uma disciplina cok o nome:" + dto.getNome());
		}
		return DisciplinaMapper.toDTO(disciplinaRepository.save(DisciplinaMapper.toEntity(dto)));
	}

	public DisciplinaResponseDTO actualizar(Long id, DisciplinaRequestDTO dto) {
		if (disciplinaRepository.existsByNome(dto.getNome())) {
			throw new ViolacaoIntegridadeException("Já existe uma disciplina com o nome:" + dto.getNome());
		}
		DisciplinaEntity d = disciplinaRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Disciplina não encontrada"));
		d.setNome(dto.getNome());
		d.setDescricao(dto.getDescricao());
		return DisciplinaMapper.toDTO(disciplinaRepository.save(d));
	}

	public void apagar(Long id) {
		if (aproveitamentoRepository.existsByDisciplinaId(id)) {
			throw new ViolacaoIntegridadeException("Não é possível apagar esse registro");
		}
		if (!disciplinaRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Disciplina não encontrada com id" + " " + id);
		}
		disciplinaRepository.deleteById(id);
	}

}
