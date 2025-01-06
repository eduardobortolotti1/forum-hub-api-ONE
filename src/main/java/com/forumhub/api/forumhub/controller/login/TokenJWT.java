package com.forumhub.api.forumhub.controller.login;

public record TokenJWT(
        String token
) {
    public TokenJWT(String token) {
        this.token = token;
    }
}
