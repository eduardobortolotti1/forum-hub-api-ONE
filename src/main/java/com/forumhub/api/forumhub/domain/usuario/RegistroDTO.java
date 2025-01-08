package com.forumhub.api.forumhub.domain.usuario;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record RegistroDTO(
        @Pattern(regexp = "\\S.*\\S", message = "O nome não pode começar ou terminar com espaços em branco.")
        @Range(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres!")
        String nome,

        @Pattern(regexp = "\\S.*\\S", message = "O email não pode começar ou terminar com espaços em branco.")
        @Email
        @Size(max = 100, message = "O email não pode ter mais que 100 caracteres")
        String email,

        @Range(min = 8, max = 150, message = "A senha deve ter entre 8 e 50 caracteres!")
        String senha
) {
}
