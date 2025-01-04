package com.forumhub.api.forumhub.domain.curso;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CursoNaoEncontradoException extends RuntimeException {
    private final String message = "Curso não encontrado";

    CursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
