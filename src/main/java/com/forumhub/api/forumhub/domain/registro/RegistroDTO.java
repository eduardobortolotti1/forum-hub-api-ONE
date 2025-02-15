package com.forumhub.api.forumhub.domain.registro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistroDTO(
        @Pattern(regexp = "^\\S(.*\\S)?$", message = "O nome não pode começar ou terminar com espaços em branco.")
        @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres!")
        String nome,

        @Pattern(regexp = "^\\S(.*\\S)?$", message = "O email não pode começar ou terminar com espaços em branco.")
        @Email(message = "O email deve ser válido!")
        @Size(max = 100, message = "O email não pode ter mais que 100 caracteres")
        String email,

        @Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos 1 letra maiúscula!")
        @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres!")
        String senha
) {
}
