package br.com.hitbox.infra.security;


import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@Builder
public class AuthenticatedUser {
    private UUID userId;

    private UUID companyId;

    private UUID teamId;

    private String email;

    private String fullName;

    private Set<String> permissions;
}
