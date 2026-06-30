package br.com.hitbox.infra.service;

import br.com.hitbox.infra.security.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserContextService {

    public UUID getUserId() {
        return getAuthenticatedUser()
                .getUserId();
    }

    public UUID getCompanyId() {
        return getAuthenticatedUser()
                .getCompanyId();
    }

    public UUID getTeamId() {
        return getAuthenticatedUser()
                .getTeamId();
    }


    public AuthenticatedUser getAuthenticatedUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || !(authentication.getPrincipal()
                instanceof AuthenticatedUserPrincipal principal)) {
            System.out.println(
                    authentication.getPrincipal().getClass()
            );
            throw new IllegalStateException(
                    "Usuário não autenticado"
            );
        }

        return principal.getUser();
    }
}
