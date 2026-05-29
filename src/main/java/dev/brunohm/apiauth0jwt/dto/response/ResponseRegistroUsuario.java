package dev.brunohm.apiauth0jwt.dto.response;

import java.time.LocalDateTime;

public record ResponseRegistroUsuario(Long id, String nome, String email, LocalDateTime criadoEm) {}
