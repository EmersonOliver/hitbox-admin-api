package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.core.domain.PricingVariable;
import br.com.hitbox.interfaces.dto.request.pricing.PricingRuleRequest;
import br.com.hitbox.interfaces.dto.response.pricing.PricingRuleResponse;
import br.com.hitbox.interfaces.dto.response.pricing.PricingVariableResponse;

public class PricingRuleMapper {

    private PricingRuleMapper() {
    }

    public static PricingRule toDomain(
            PricingRuleRequest request) {

        return PricingRule.builder()
                .name(request.getName())
                .active(request.getActive())
                .minimumPrice(request.getMinimumPrice())
                .energyCostPerHour(
                        request.getEnergyCostPerHour())
                .machineHourCost(
                        request.getMachineHourCost())
                .laborHourCost(
                        request.getLaborHourCost())
                .maintenanceRate(
                        request.getMaintenanceRate())
                .indirectCost(
                        request.getIndirectCost())
                .administrativeCost(
                        request.getAdministrativeCost())

                .profitMargin(
                        request.getProfitMargin())
                .safetyMargin(
                        request.getSafetyMargin())
                .commercialCommission(
                        request.getCommercialCommission())
                .minimumMarkup(
                        request.getMinimumMarkup())
                .lossReserve(
                        request.getLossReserve())

                .marketplaceFee(
                        request.getMarketplaceFee())
                .cardFee(
                        request.getCardFee())
                .taxFee(
                        request.getTaxFee())
                .pixFee(
                        request.getPixFee())
                .gatewayFee(
                        request.getGatewayFee())
                .otherFee(
                        request.getOtherFee())

                .variables(
                        request.getVariables()
                                .stream()
                                .map(v ->
                                        PricingVariable.builder()
                                                .name(v.getName())
                                                .type(v.getType())
                                                .unit(v.getUnit())
                                                .required(v.getRequired())
                                                .impactValue(v.getImpactValue())
                                                .impactType(v.getImpactType())
                                                .build())
                                .toList())
                .build();
    }

    public static PricingRuleResponse toResponse(
            PricingRule domain
    ) {

        return PricingRuleResponse.builder()

                .id(domain.getId())
                .name(domain.getName())
                .active(domain.getActive())

                .minimumPrice(domain.getMinimumPrice())

                /*
                 * Custos Operacionais
                 */

                .energyCostPerHour(domain.getEnergyCostPerHour())
                .machineHourCost(domain.getMachineHourCost())
                .laborHourCost(domain.getLaborHourCost())
                .maintenanceRate(domain.getMaintenanceRate())
                .indirectCost(domain.getIndirectCost())
                .administrativeCost(domain.getAdministrativeCost())
                .lastUpdate(domain.getLastUpdate())
                /*
                 * Margens
                 */

                .profitMargin(domain.getProfitMargin())
                .safetyMargin(domain.getSafetyMargin())
                .commercialCommission(domain.getCommercialCommission())
                .minimumMarkup(domain.getMinimumMarkup())
                .lossReserve(domain.getLossReserve())

                /*
                 * Taxas
                 */

                .marketplaceFee(domain.getMarketplaceFee())
                .cardFee(domain.getCardFee())
                .taxFee(domain.getTaxFee())
                .pixFee(domain.getPixFee())
                .gatewayFee(domain.getGatewayFee())
                .otherFee(domain.getOtherFee())

                /*
                 * Variáveis
                 */

                .variables(
                        domain.getVariables()
                                .stream()
                                .map(variable ->
                                        PricingVariableResponse.builder()
                                                .id(variable.getId())
                                                .name(variable.getName())
                                                .type(variable.getType())
                                                .unit(variable.getUnit())
                                                .required(variable.getRequired())
                                                .impactValue(variable.getImpactValue())
                                                .impactType(variable.getImpactType())
                                                .build()
                                )
                                .toList()
                )

                .build();
    }
}