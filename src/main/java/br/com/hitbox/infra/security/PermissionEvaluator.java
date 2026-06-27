package br.com.hitbox.infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("permission")
public class PermissionEvaluator {
    public boolean has(
            Authentication authentication,
            String permission
    ) {

        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals(permission)
                );
    }
}
