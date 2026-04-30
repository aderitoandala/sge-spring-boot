package com.mz.sge.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mz.sge.dto.ApiErrorResponse;
import com.mz.sge.dto.ApiValidationErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class TratadorGlobalException {

//Not Found
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ApiErrorResponse> recursoNaoEncontradoHandler(RecursoNaoEncontradoException ex,
			HttpServletRequest request) {
		log.warn("Recurso não encontrado.caminho:{}", request.getRequestURI(), ex);

		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(new ApiErrorResponse(status.value(), status.getReasonPhrase(),
				ex.getMessage(), request.getRequestURI()));
	}

//Internal server error
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiErrorResponse> erroInternoHandler(RuntimeException ex, HttpServletRequest request) {
		log.error("erro interno", ex);

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(status).body(new ApiErrorResponse(status.value(), status.getReasonPhrase(),
				"Ocorreu um erro no servidor. Tente mais tarde", request.getRequestURI()));
	}

//Conflict
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> globalDataIntegrityViolationHandler(DataIntegrityViolationException ex,
			HttpServletRequest request) {
		log.warn("erro de integridade no caminho:{}", request.getRequestURI(), ex);

		HttpStatus status = HttpStatus.CONFLICT;
		return ResponseEntity.status(status).body(new ApiErrorResponse(status.value(), status.getReasonPhrase(),
				"Violação de integridade dos dados", request.getRequestURI()));
	}

//Bad request - erro de validacao de campos
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiValidationErrorResponse> erroValidacaoHandler(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		log.warn("erro de validação no caminho:{}", request.getRequestURI(), ex);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		Map<String, String> fields = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(field -> fields.put(field.getField(), field.getDefaultMessage()));

		return ResponseEntity.status(status)
				.body(new ApiValidationErrorResponse(status.value(), status.getReasonPhrase(),
						"Houve falha na validação de um ou  mais campos", fields, request.getRequestURI()));
	}

//Bad request - erro de conversão de tipos
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiErrorResponse> erroConversaoTiposHandler(MethodArgumentTypeMismatchException ex,
			HttpServletRequest request) {
		log.warn("erro de conversão de tipos.caminho:{}", request.getRequestURI(), ex);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(new ApiErrorResponse(status.value(), status.getReasonPhrase(),
				"dado inválido para o campo" + " " + ex.getName(), request.getRequestURI()));

	}

	@ExceptionHandler(ViolacaoIntegridadeException.class)
	public ResponseEntity<ApiErrorResponse> violacaoIntegridadeHandler(ViolacaoIntegridadeException ex,
			HttpServletRequest request) {
		log.warn("violação de integridade.caminho:{}", request.getRequestURI(), ex);

		HttpStatus status = HttpStatus.CONFLICT;
		return ResponseEntity.status(status).body(new ApiErrorResponse(status.value(), status.getReasonPhrase(),
				ex.getMessage(), request.getRequestURI()));
	}

}
