package com.mz.sge.auth.security;
import com.mz.sge.auth.user.CustomUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mz.sge.auth.exception.TokenGenerationException;

import java.time.Instant;
import java.time.Duration;


@Service
public class JwtAuthenticationService{

@Value("${JWT_SECRET}")
private String secret;


private Instant generateExpirationDate(){
return Instant.now().plus(Duration.ofHours(1));
}


public String generateToken(CustomUser user){
try{
Algorithm algorithm = Algorithm.HMAC256(secret);
String token =JWT.create()
	.withIssuer("auth-api")
	.withSubject(user.getUsername())
	.withExpiresAt(generateExpirationDate())
	.sign(algorithm);

	return token;

} catch(JWTCreationException ex){

throw new TokenGenerationException("Erro ao gerar token");

}
}


public String validateToken(String token){
    try{
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
   }catch(JWTVerificationException ex){
        return null;
    }

}







}









