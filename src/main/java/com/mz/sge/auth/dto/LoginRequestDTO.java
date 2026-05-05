package com.mz.sge.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(@NotBlank(message = "O username é obrigatório") String username,
		@NotBlank(message = "A senha é obrigatória") @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres") String password) {
}
