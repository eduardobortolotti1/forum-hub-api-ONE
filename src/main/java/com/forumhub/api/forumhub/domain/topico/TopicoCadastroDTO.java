package com.forumhub.api.forumhub.domain.topico;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicoCadastroDTO(
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