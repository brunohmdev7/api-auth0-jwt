package dev.brunohm.apiauth0jwt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestLoginUsuario(
        @Email
        @NotBlank
        String email,

        @NotBlank
        String senha) {
}
