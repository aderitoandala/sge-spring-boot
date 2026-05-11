package com.mz.sge.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mz.sge.auth.dto.AuthErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.mz.sge.auth")
public class AuthExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<AuthErrorResponse> userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex,
			HttpServletRequest request) {

		log.warn("Tentativa de duplicar usuário.path:{}", request.getRequestURI(), ex);

		HttpStatus status = HttpStatus.CONFLICT;

		AuthErrorResponse response = new AuthErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
				request.getServletPath());

		return ResponseEntity.status(status).body(response);
	}

@ExceptionHandler(TokenGenerationException.class)
public ResponseEntity<AuthErrorResponse> tokenGenerationHandler(TokenGenerationException ex,HttpServletRequest request ){
log.error("error while generate token.path:{}",request.getServletPath(),ex);

HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
AuthErrorResponse response = new AuthErrorResponse(status.value(),status.getReasonPhrase(),"Login failed.Please try again later!",request.getRequestURI());
return ResponseEntity.status(status).body(response);
}


@ExceptionHandler(UsernameNotFoundException.class)
public ResponseEntity<AuthErrorResponse> usernameNotFoundHandler(UsernameNotFoundException ex,HttpServletRequest request ){
log.warn("user not found.path:{}",request.getServletPath(),ex);

HttpStatus status = HttpStatus.UNAUTHORIZED;
AuthErrorResponse response = new AuthErrorResponse(status.value(),status.getReasonPhrase(),"Invalid credentials",request.getRequestURI());
return ResponseEntity.status(status).body(response);
}

@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<AuthErrorResponse> badCredentialsHandler(BadCredentialsException ex, HttpServletRequest request) {
    log.warn("authentication failed.path:{}", request.getServletPath());

    HttpStatus status = HttpStatus.UNAUTHORIZED;
    AuthErrorResponse response = new AuthErrorResponse(status.value(),status.getReasonPhrase(),"Invalid credentials",request.getRequestURI());
    return ResponseEntity.status(status).body(response);
}


}
