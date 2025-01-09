package com.forumhub.api.forumhub.controller.topico;

public class EmailJaExistenteException extends RuntimeException {
    public EmailJaExistenteException() {
        super("Email já cadastrado");
    }
}
