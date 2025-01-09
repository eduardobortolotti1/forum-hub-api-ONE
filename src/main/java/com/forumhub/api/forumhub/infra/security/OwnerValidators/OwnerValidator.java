package com.forumhub.api.forumhub.infra.security.OwnerValidators;

import com.forumhub.api.forumhub.infra.security.SecurityContexHolderAccess;

// Implements variables from my CustomAuthenticationToken Security Context.
// I have no idea if this is really the most optimal solution, but hey, it works.
public interface OwnerValidator extends SecurityContexHolderAccess {
    void validate(Long EntityId);
}
