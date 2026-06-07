package br.com.hitbox.infra.jpa;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupTest {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {

        Session session =
                entityManager.unwrap(Session.class);

        try {

            session.enableFilter("tenantFilter");

            System.out.println(
                    "FILTER REGISTERED"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
