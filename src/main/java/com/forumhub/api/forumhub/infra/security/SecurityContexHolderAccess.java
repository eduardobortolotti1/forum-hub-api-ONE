package com.forumhub.api.forumhub.infra.security;

import org.springframework.security.core.context.SecurityContextHolder;

// Implements variables from my CustomAuthenticationToken Security Context.
// I have no idea if this is really the most optimal solution, but hey, it works.
public interface SecurityContexHolderAccess {
    CustomAuthenticationToken authContext = (CustomAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    String authContextPrincipal = authContext.getName();
    String authContextToken = authContext.getToken();
    Long authContextUserId = authContext.getUserId();
}
