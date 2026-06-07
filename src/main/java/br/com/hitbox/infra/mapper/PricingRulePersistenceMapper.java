package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.infra.entity.PricingRuleEntity;

public class PricingRulePersistenceMapper {

    private PricingRulePersistenceMapper() {
    }

    public static PricingRule toDomain(
            PricingRuleEntity entity
    ) {

        return PricingRule.builder()
                .id(entity.getId())
                .name(entity.getName())
                .salesChannel(entity.getSalesChannel())
                .commercialCost(entity.getCommercialCost())
                .operationalCost(entity.getOperationalCost())
                .profitMargin(entity.getProfitMargin())
                .minimumPrice(entity.getMinimumPrice())
                .marketplaceFee(entity.getMarketplaceFee())
                .cardFee(entity.getCardFee())
                .active(entity.getActive())
                .companyId(entity.getCompanyId())
                .build();
    }

    public static PricingRuleEntity toEntity(PricingRule domain) {
        return PricingRuleEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .salesChannel(domain.getSalesChannel())
                .commercialCost(domain.getCommercialCost())
                .operationalCost(domain.getOperationalCost())
                .profitMargin(domain.getProfitMargin())
                .minimumPrice(domain.getMinimumPrice())
                .marketplaceFee(domain.getMarketplaceFee())
                .cardFee(domain.getCardFee())
                .active(domain.getActive())
                .companyId(domain.getCompanyId())
                .build();
    }
}