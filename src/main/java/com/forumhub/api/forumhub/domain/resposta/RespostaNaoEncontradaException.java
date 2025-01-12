package com.forumhub.api.forumhub.domain.resposta;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RespostaNaoEncontradaException extends RuntimeException {
    private String message = "Esta resposta não existe!";
}
