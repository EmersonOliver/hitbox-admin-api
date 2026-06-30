package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.core.domain.PricingVariable;
import br.com.hitbox.infra.entity.PricingRuleEntity;
import br.com.hitbox.infra.entity.PricingVariableEntity;

public class PricingRulePersistenceMapper {

    private PricingRulePersistenceMapper() {
    }

    public static PricingRule toDomain(
            PricingRuleEntity entity
    ) {

        return PricingRule.builder()

                .id(entity.getId())

                .companyId(entity.getCompanyId())

                .name(entity.getName())

                .active(entity.getActive())

                .minimumPrice(
                        entity.getMinimumPrice()
                )

                /*
                 * Custos Operacionais
                 */

                .energyCostPerHour(
                        entity.getEnergyCostPerHour()
                )

                .machineHourCost(
                        entity.getMachineHourCost()
                )

                .laborHourCost(
                        entity.getLaborHourCost()
                )

                .maintenanceRate(
                        entity.getMaintenanceRate()
                )

                .indirectCost(
                        entity.getIndirectCost()
                )

                .administrativeCost(
                        entity.getAdministrativeCost()
                )

                /*
                 * Margens
                 */

                .profitMargin(
                        entity.getProfitMargin()
                )

                .safetyMargin(
                        entity.getSafetyMargin()
                )

                .commercialCommission(
                        entity.getCommercialCommission()
                )

                .minimumMarkup(
                        entity.getMinimumMarkup()
                )

                .lossReserve(
                        entity.getLossReserve()
                )

                /*
                 * Taxas
                 */

                .marketplaceFee(
                        entity.getMarketplaceFee()
                )

                .cardFee(
                        entity.getCardFee()
                )

                .taxFee(
                        entity.getTaxFee()
                )

                .pixFee(
                        entity.getPixFee()
                )

                .gatewayFee(
                        entity.getGatewayFee()
                )

                .otherFee(
                        entity.getOtherFee()
                )

                /*
                 * Variáveis
                 */

                .variables(
                        entity.getVariables()
                                .stream()
                                .map(variable ->
                                        PricingVariable.builder()
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

    public static PricingRuleEntity toEntity(
            PricingRule domain) {

        PricingRuleEntity entity =
                PricingRuleEntity.builder()
                        .id(domain.getId())
                        .companyId(domain.getCompanyId())
                        .name(domain.getName())
                        .active(domain.getActive())

                        .minimumPrice(domain.getMinimumPrice())

                        .energyCostPerHour(
                                domain.getEnergyCostPerHour())

                        .machineHourCost(
                                domain.getMachineHourCost())

                        .laborHourCost(
                                domain.getLaborHourCost())

                        .maintenanceRate(
                                domain.getMaintenanceRate())

                        .indirectCost(
                                domain.getIndirectCost())

                        .administrativeCost(
                                domain.getAdministrativeCost())

                        .profitMargin(
                                domain.getProfitMargin())

                        .safetyMargin(
                                domain.getSafetyMargin())

                        .commercialCommission(
                                domain.getCommercialCommission())

                        .minimumMarkup(
                                domain.getMinimumMarkup())

                        .lossReserve(
                                domain.getLossReserve())

                        .marketplaceFee(
                                domain.getMarketplaceFee())

                        .cardFee(
                                domain.getCardFee())

                        .taxFee(
                                domain.getTaxFee())

                        .pixFee(
                                domain.getPixFee())

                        .gatewayFee(
                                domain.getGatewayFee())

                        .otherFee(
                                domain.getOtherFee())

                        .build();

        entity.setVariables(
                domain.getVariables()
                        .stream()
                        .map(v ->
                                PricingVariableEntity.builder()
                                        .name(v.getName())
                                        .type(v.getType())
                                        .unit(v.getUnit())
                                        .required(v.getRequired())
                                        .impactValue(v.getImpactValue())
                                        .impactType(v.getImpactType())
                                        .pricingRule(entity)
                                        .build())
                        .toList()
        );

        return entity;
    }
}