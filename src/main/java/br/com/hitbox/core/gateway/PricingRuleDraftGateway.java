package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.PricingRuleDraft;

import java.util.Optional;
import java.util.UUID;

public interface PricingRuleDraftGateway {
    void save(
            PricingRuleDraft draft
    );

    Optional<PricingRuleDraft> find(
            UUID userId,
            UUID companyId
    );

    void delete(
            UUID userId,
            UUID companyId
    );
}
