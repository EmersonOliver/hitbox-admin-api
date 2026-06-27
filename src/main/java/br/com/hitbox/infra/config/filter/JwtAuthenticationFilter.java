package br.com.hitbox.infra.config.filter;

import br.com.hitbox.infra.security.TokenService;
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
            DecodedJWT jwt =
                    tokenService.decode(token);
            UUID userId =
                    UUID.fromString(
                            jwt.getSubject()
                    );

            List<String> permissions =
                    jwt.getClaim("permissions")
                            .asList(String.class);

            List<GrantedAuthority> authorities =
                    permissions.stream()
                            .map(permission -> (GrantedAuthority) new SimpleGrantedAuthority(permission))
                            .toList();

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            authorities
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);
        }


        filterChain.doFilter(
                request,
                response
        );
    }

    public String recoverToken(HttpServletRequest httpServletRequest) {
        var authorization = httpServletRequest.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }
}
