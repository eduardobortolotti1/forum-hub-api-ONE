package com.forumhub.api.forumhub.controller.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @Email
        String email,
        @NotBlank
        String senha
) {
}
