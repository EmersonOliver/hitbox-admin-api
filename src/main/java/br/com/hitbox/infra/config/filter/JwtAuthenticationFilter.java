package br.com.hitbox.infra.config.filter;

import br.com.hitbox.infra.security.AuthenticatedUser;
import br.com.hitbox.infra.security.TokenService;
import br.com.hitbox.infra.service.AuthenticatedUserPrincipal;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token =
                recoverToken(request);


        if (token != null) {
            this.teste(token);
//            DecodedJWT jwt =
//                    tokenService.decode(token);
//            UUID userId =
//                    UUID.fromString(
//                            jwt.getSubject()
//                    );
//
//            List<String> permissions =
//                    jwt.getClaim("permissions")
//                            .asList(String.class);
//
//            List<GrantedAuthority> authorities =
//                    permissions.stream()
//                            .map(permission -> (GrantedAuthority) new SimpleGrantedAuthority(permission))
//                            .toList();
//
//            UsernamePasswordAuthenticationToken auth =
//                    new UsernamePasswordAuthenticationToken(
//                            userId,
//                            null,
//                            authorities
//                    );
//
//            SecurityContextHolder
//                    .getContext()
//                    .setAuthentication(auth);
        }


        filterChain.doFilter(
                request,
                response
        );
    }

    private void teste(String token) {
        DecodedJWT jwt =
                tokenService.decode(token);
        UUID userId =
                UUID.fromString(
                        jwt.getSubject()
                );

        UUID companyId = UUID.fromString(jwt.getClaim("X-Company-Id").asString());

        UUID teamId = UUID.fromString(jwt.getClaim("X-Team-Id").asString());

        String email = jwt.getClaim("email").asString();


        List<String> permissions =
                jwt.getClaim("permissions")
                        .asList(String.class);

        List<GrantedAuthority> authorities =
                permissions.stream()
                        .map(permission -> (GrantedAuthority) new SimpleGrantedAuthority(permission))
                        .toList();

        AuthenticatedUserPrincipal principal =
                new AuthenticatedUserPrincipal(
                        AuthenticatedUser.builder()
                                .userId(userId)
                                .companyId(companyId)
                                .teamId(teamId)
                                .email(email)
                                .permissions(new HashSet<>(permissions))
                                .build(),
                        authorities
                );

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        authorities
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }

    public String recoverToken(HttpServletRequest httpServletRequest) {
        var authorization = httpServletRequest.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }
}
