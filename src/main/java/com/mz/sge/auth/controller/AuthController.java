package com.mz.sge.auth.controller;
import com.mz.sge.auth.dto.UserRegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mz.sge.auth.service.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import com.mz.sge.auth.security.JwtAuthenticationService;
import com.mz.sge.auth.user.CustomUser;
import com.mz.sge.auth.dto.LoginRequestDTO;
import com.mz.sge.auth.dto.LoginResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.mz.sge.auth.exception.TokenGenerationException;


@RestController
@RequestMapping("/auth")
public class AuthController{

private final CustomUserDetailsService userService;

private final JwtAuthenticationService tokenService;

private final AuthenticationManager authenticationManager;

public AuthController(CustomUserDetailsService userService, JwtAuthenticationService tokenService, AuthenticationManager authenticationManager){
this.userService=userService;
this.tokenService=tokenService;
this.authenticationManager=authenticationManager;
}

@PostMapping("/register")
public ResponseEntity<Void> registerUser(@RequestBody UserRegisterDTO data){
this.userService.registerUser(data);
return ResponseEntity.status(HttpStatus.CREATED).build();
}

@PostMapping("/login")
public ResponseEntity<LoginResponseDTO>login(@RequestBody @Valid LoginRequestDTO data){
var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.username(),data.password()));
if(!(auth.getPrincipal() instanceof CustomUser user)){
   throw new TokenGenerationException("Tipo de usuário inesperado");
}
String token = this.tokenService.generateToken(user);

return ResponseEntity.ok(new LoginResponseDTO(token));
}






}
