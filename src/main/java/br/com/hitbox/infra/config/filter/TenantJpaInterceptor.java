package br.com.hitbox.infra.config.filter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TenantJpaInterceptor {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void enableFilter() {

        UUID companyId = TenantContext.getCompanyId();

        if (companyId == null) {
            return;
        }

        entityManager.unwrap(Session.class)
                .enableFilter("tenantFilter")
                .setParameter(
                        "companyId",
                        companyId
                );
    }
}
