package dev.brunohm.apiauth0jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import dev.brunohm.apiauth0jwt.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.token-service.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("api-auth0-jwt")
                .withSubject(usuario.getUsername())
                .withExpiresAt(dataExpiracao())
                .sign(algorithm);
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
