package com.forumhub.api.forumhub.domain.topico;

import jakarta.validation.constraints.*;

public record TopicoCadastroDTO(
        @Pattern(regexp = "\\S.*\\S", message = "O título não pode começar ou terminar com espaços em branco.")
        @Size(min = 5, max = 150, message = "Digite um título de 5 até 150 caracteres!")
        @NotBlank
        String titulo,

        @Size(min = 5, max = 10000, message = "Digite uma mensagem de 5 até 10.000 caracteres!")
        @NotBlank
        String mensagem,

        @NotNull
        Long idAutor,

        @NotNull
        Long idCurso
) {
}