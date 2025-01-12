package com.forumhub.api.forumhub.domain.registro;

public class EmailJaExistenteException extends RuntimeException {
    public EmailJaExistenteException() {
        super("Email já cadastrado");
    }
}
