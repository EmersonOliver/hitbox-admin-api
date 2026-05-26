package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.interfaces.dto.request.pricing.PricingRuleRequest;
import br.com.hitbox.interfaces.dto.response.pricing.PricingRuleResponse;

public class PricingRuleMapper {

    private PricingRuleMapper() {
    }

    public static PricingRule toDomain(PricingRuleRequest request) {
        return PricingRule.builder()
                .name(request.getName())
                .salesChannel(request.getSalesChannel())
                .commercialCost(request.getCommercialCost())
                .operationalCost(request.getOperationalCost())
                .profitMargin(request.getProfitMargin())
                .minimumPrice(request.getMinimumPrice())
                .marketplaceFee(request.getMarketplaceFee())
                .cardFee(request.getCardFee())
                .active(request.getActive())
                .build();
    }

    public static PricingRuleResponse toResponse(PricingRule domain) {
        return PricingRuleResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .salesChannel(domain.getSalesChannel())
                .operationalCost(domain.getOperationalCost())
                .commercialCost(domain.getCommercialCost())
                .profitMargin(domain.getProfitMargin())
                .minimumPrice(domain.getMinimumPrice())
                .marketplaceFee(domain.getMarketplaceFee())
                .cardFee(domain.getCardFee())
                .active(domain.getActive())
                .build();
    }
}