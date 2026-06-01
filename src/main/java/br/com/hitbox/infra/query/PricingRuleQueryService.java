package br.com.hitbox.infra.query;

import br.com.hitbox.infra.entity.PricingRuleEntity;
import br.com.hitbox.infra.jpa.SpringDataPricingRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PricingRuleQueryService {

    private final SpringDataPricingRuleRepository jpaRepository;

    public Long countAll() {
        return jpaRepository.count();
    }

    public Optional<PricingRuleEntity> findById(Long id) {
        return jpaRepository.findById(id);
    }


}
