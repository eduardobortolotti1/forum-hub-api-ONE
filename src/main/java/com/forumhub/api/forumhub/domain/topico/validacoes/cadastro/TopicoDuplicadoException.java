package com.forumhub.api.forumhub.domain.topico.validacoes.cadastro;

public class TopicoDuplicadoException extends RuntimeException {
    public TopicoDuplicadoException(String message) {
        super(message);
    }
}
