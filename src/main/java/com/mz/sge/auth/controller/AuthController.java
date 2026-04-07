package com.mz.sge.auth.controller;
import com.mz.sge.auth.dto.UserRegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mz.sge.auth.service.CustomUserDetailsService;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/auth")
public class AuthController{

private final CustomUserDetailsService userService;

public AuthController(CustomUserDetailsService userService){
this.userService=userService;
}

@PostMapping("/register")
public ResponseEntity<Void> registerUser(@RequestBody UserRegisterDTO data){
this.userService.registerUser(data);
return ResponseEntity.status(HttpStatus.CREATED).build();
}






}
