package com.mz.sge.auth.security;
import com.mz.sge.auth.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

private final JwtAuthenticationService jwtAuthenticationService;
private final UserRepository userRepository;

public JwtAuthenticationFilter(JwtAuthenticationService jwtAuthenticationService, UserRepository userRepository){
this.jwtAuthenticationService=jwtAuthenticationService;
this.userRepository=userRepository;
}

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain)throws ServletException,IOException{
String token = this.extractToken(request);

if(token!=null){
String username = this.jwtAuthenticationService.validateToken(token);
if(username!=null){
   UserDetails user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
   var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
   SecurityContextHolder.getContext().setAuthentication(authentication);
}
}
filterChain.doFilter(request,response);
}


public String extractToken(HttpServletRequest request){
var authHeader=request.getHeader("Authorization");
if(authHeader!=null && authHeader.startsWith("Bearer ")){
        return authHeader.substring(7);
}
return null;
}



}








