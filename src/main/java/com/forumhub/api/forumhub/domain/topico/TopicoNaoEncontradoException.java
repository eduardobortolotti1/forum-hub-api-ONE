package com.forumhub.api.forumhub.domain.topico;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TopicoNaoEncontradoException extends RuntimeException {

    private final String message = "Tópico não encontrado";

    TopicoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
