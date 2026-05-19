package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.PricingRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPricingRuleRepository extends JpaRepository<PricingRuleEntity, Long> {
}
