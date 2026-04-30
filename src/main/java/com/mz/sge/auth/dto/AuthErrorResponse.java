package com.mz.sge.auth.dto;

import java.time.LocalDateTime;

public record AuthErrorResponse(int status, String title, String message, LocalDateTime timestamp, String path) {
	public AuthErrorResponse(int status, String title, String message, String path) {

		this(status, title, message, LocalDateTime.now(), path);

	}

}
