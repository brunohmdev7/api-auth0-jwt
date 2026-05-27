package dev.brunohm.apiauth0jwt.dto;

import java.time.LocalDateTime;

public record ResponseLoginUsuario(String nome, String email, LocalDateTime logouEm) {
}
