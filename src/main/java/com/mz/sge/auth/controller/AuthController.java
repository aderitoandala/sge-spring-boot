package com.mz.sge.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mz.sge.auth.dto.LoginRequestDTO;
import com.mz.sge.auth.dto.LoginResponseDTO;
import com.mz.sge.auth.dto.UserRegisterDTO;
import com.mz.sge.auth.exception.TokenGenerationException;
import com.mz.sge.auth.security.JwtAuthenticationService;
import com.mz.sge.auth.service.CustomUserDetailsService;
import com.mz.sge.auth.user.CustomUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "1. Auth", description = "Autenticação e Autorização")
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final CustomUserDetailsService userService;

	private final JwtAuthenticationService tokenService;

	private final AuthenticationManager authenticationManager;

	public AuthController(CustomUserDetailsService userService, JwtAuthenticationService tokenService,
			AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@Operation(summary = "Registar novo usuário com role USER")
	@ApiResponse(responseCode = "201", description = "Usuário Registado")
	@ApiResponse(responseCode = "409", description = "Usuário já existe")
	@ApiResponse(responseCode = "400", description = "Dados inválidos")
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody  @Valid UserRegisterDTO data) {
		this.userService.registerUser(data);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(summary = "Login")
	@ApiResponse(responseCode = "200", description = "Autenticado(token)")
	@ApiResponse(responseCode = "401", description = "Não autenticado")
	@ApiResponse(responseCode = "400", description = "Dados inválidos")
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
		var auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(data.username(), data.password()));
		if (!(auth.getPrincipal() instanceof CustomUser user)) {
			throw new TokenGenerationException("Tipo de usuário inesperado");
		}
		String token = this.tokenService.generateToken(user);

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

}
