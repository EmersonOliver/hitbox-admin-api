package br.com.hitbox.infra.config.filter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class TenantFilterAspect {
    @PersistenceContext
    private EntityManager entityManager;

    @Before("""
        execution(* br.com.hitbox.infra.jpa..*.*(..))
        """)
    public void enableTenantFilter() {

        UUID companyId =
                TenantContext.getCompanyId();

        if (companyId == null) {
            return;
        }

        Session session =
                entityManager.unwrap(Session.class);

        if (session.getEnabledFilter("tenantFilter") == null) {

            session.enableFilter("tenantFilter")
                    .setParameter(
                            "companyId",
                            companyId
                    );

            log.debug(
                    "Tenant filter enabled for company {}",
                    companyId
            );
        }
    }
}
