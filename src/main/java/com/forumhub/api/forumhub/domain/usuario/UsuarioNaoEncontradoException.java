package com.forumhub.api.forumhub.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioNaoEncontradoException extends RuntimeException {

    private final String message = "Usuário não encontrado";

    UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
