package br.imd.ufrn.feirinhas_ufrn.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String jwtSecret;

  @Value("${spring.application.name}")
  private String appName;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
      
      String token = JWT
        .create()
        .withIssuer(appName)
        .withSubject(user.getEmail())
        .withExpiresAt(genExpirationDate())
        .sign(algorithm);

      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Erro ao gerar o token JWT");
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
      return JWT.require(algorithm)
        .withIssuer(appName)
        .build()
        .verify(token)
        .getSubject();
    } catch (JWTVerificationException e) {
        return "";
    }
  }

  private Instant genExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
