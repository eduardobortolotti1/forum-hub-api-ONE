package com.forumhub.api.forumhub.infra;

public record TokenJWT(
        String token
) {
    public TokenJWT(String token) {
        this.token = token;
    }
}
