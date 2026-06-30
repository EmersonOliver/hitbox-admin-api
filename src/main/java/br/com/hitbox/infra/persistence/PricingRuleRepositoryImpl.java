package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.infra.config.filter.TenantContext;
import br.com.hitbox.infra.entity.PricingRuleEntity;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataCategoriaRepository;
import br.com.hitbox.infra.jpa.SpringDataPricingRuleRepository;
import br.com.hitbox.infra.mapper.PricingRulePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PricingRuleRepositoryImpl {

    private final SpringDataPricingRuleRepository repository;

    private final SpringDataCategoriaRepository categoriaRepository;

    public PricingRule salvar(PricingRule domain) {
        PricingRuleEntity entity =
                PricingRulePersistenceMapper
                        .toEntity(domain);
        entity = repository.save(entity);
        return PricingRulePersistenceMapper
                .toDomain(entity);
    }

    public Page<PricingRule> page(
            Pageable pageable
    ) {

        return repository
                .findAll(pageable)
                .map(
                        PricingRulePersistenceMapper::toDomain
                );
    }

    public List<PricingRule> findAll() {
        return this.repository.findAll().stream().map(PricingRulePersistenceMapper::toDomain).toList();
    }

    public PricingRule editar(PricingRule domain) {

        PricingRuleEntity pricingRule = this.repository.findById(domain.getId())
                .orElseThrow(() -> new HitboxException("Nenhuma regra encontrada!"));

        var existsRule = this.repository.findByNameAndChannel(
                domain.getName().toUpperCase()
        );

        if (existsRule.isPresent() && !existsRule.get().getId().equals(domain.getId())) {
            throw new HitboxException(
                    "Já existe uma regra cadastrada com este nome para o mesmo canal!"
            );
        }
        pricingRule.setName(domain.getName());
        pricingRule.setMinimumPrice(domain.getMinimumPrice());
        pricingRule.setActive(domain.getActive());
        pricingRule.setCardFee(domain.getCardFee());
        pricingRule.setMarketplaceFee(domain.getMarketplaceFee());
        pricingRule.setProfitMargin(domain.getProfitMargin());

        pricingRule = repository.save(pricingRule);
        return PricingRulePersistenceMapper
                .toDomain(pricingRule);
    }

    public void deleteRuleBy(Long ruleId) {
        PricingRuleEntity ruleEntity = this.repository.findById(ruleId)
                .orElseThrow(() -> new HitboxException("Regra não encontrada"));
        repository.delete(ruleEntity);
    }
}