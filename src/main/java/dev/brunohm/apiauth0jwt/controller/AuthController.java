package dev.brunohm.apiauth0jwt.controller;

import dev.brunohm.apiauth0jwt.dto.request.RequestLoginUsuario;
import dev.brunohm.apiauth0jwt.dto.response.ResponseTokenJWT;
import dev.brunohm.apiauth0jwt.entity.Usuario;
import dev.brunohm.apiauth0jwt.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity fazerLogin(@RequestBody @Valid RequestLoginUsuario dtoRequest) {
        var tokenAuth = new UsernamePasswordAuthenticationToken(dtoRequest.email(), dtoRequest.senha());
        var authentication = authenticationManager.authenticate(tokenAuth);

        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseTokenJWT(tokenJwt));
    }
}