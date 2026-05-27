package dev.brunohm.apiauth0jwt.dto;

import java.time.LocalDateTime;

public record ResponseRegistroUsuario(String nome, String email, LocalDateTime criadoEm) {}
