package br.com.hitbox.infra.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@Order(1)
public class TenantFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String companyId =
                request.getHeader(
                        "X-Company-Id"
                );

        if (companyId != null) {
            TenantContext.setCompanyId(
                    UUID.fromString(companyId)
            );
        }

        try {
            chain.doFilter(
                    request,
                    response
            );
        } finally {
            TenantContext.clear();
        }
    }
}
