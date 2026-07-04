package br.com.hitbox.infra.service;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.core.domain.ProductPricingContext;
import br.com.hitbox.interfaces.dto.response.pricing.SuggestedPriceResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PricingEngineService {

    public SuggestedPriceResult calculate(
            ProductPricingContext context,
            PricingRule rule
    ) {

        BigDecimal quantity =
                BigDecimal.valueOf(
                        context.getQuantity() == null
                                ? 1
                                : context.getQuantity()
                );

        /*
         * FILAMENTO
         */

        BigDecimal filamentCost =
                safe(context.getFilamentWeight())
                        .multiply(
                                safe(context.getFilamentCostPerGram())
                        );

        /*
         * HORA MÁQUINA
         */

        BigDecimal machineCost =
                safe(context.getPrintHours())
                        .multiply(
                                safe(rule.getMachineHourCost())
                        );

        /*
         * MÃO DE OBRA
         */

        BigDecimal laborCost =
                safe(context.getPrintHours())
                        .multiply(
                                safe(rule.getLaborHourCost())
                        );

        /*
         * ENERGIA
         */

        BigDecimal energyCost =
                safe(context.getPrintHours())
                        .multiply(
                                safe(rule.getEnergyCostPerHour())
                        );

        /*
         * EXTRAS
         */

        BigDecimal extrasCost =
                calculateExtras(
                        context,
                        quantity
                );

        /*
         * CUSTO DE PRODUÇÃO
         */

        BigDecimal productionCost =
                filamentCost
                        .add(machineCost)
                        .add(laborCost)
                        .add(energyCost)
                        .add(extrasCost);

        /*
         * MANUTENÇÃO
         */

        BigDecimal maintenanceCost =
                productionCost.multiply(
                        safe(rule.getMaintenanceRate())
                                .divide(
                                        BigDecimal.valueOf(100),
                                        4,
                                        RoundingMode.HALF_UP
                                )
                );

        /*
         * CUSTOS INDIRETOS
         */

        BigDecimal indirectCost =
                safe(rule.getIndirectCost());

        BigDecimal administrativeCost =
                safe(rule.getAdministrativeCost());

        /*
         * CUSTO BASE
         */

        BigDecimal baseCost =
                productionCost
                        .add(maintenanceCost)
                        .add(indirectCost)
                        .add(administrativeCost);

        /*
         * MARGENS
         */

        BigDecimal totalMarginPercent =
                safe(rule.getProfitMargin())
                        .add(
                                safe(rule.getSafetyMargin())
                        )
                        .add(
                                safe(rule.getCommercialCommission())
                        )
                        .add(
                                safe(rule.getLossReserve())
                        );

        BigDecimal priceWithMargin =
                baseCost.multiply(
                        BigDecimal.ONE.add(
                                totalMarginPercent.divide(
                                        BigDecimal.valueOf(100),
                                        4,
                                        RoundingMode.HALF_UP
                                )
                        )
                );

        /*
         * TAXAS
         */

        BigDecimal totalFeesPercent =
                safe(rule.getMarketplaceFee())
                        .add(
                                safe(rule.getCardFee())
                        )
                        .add(
                                safe(rule.getTaxFee())
                        )
                        .add(
                                safe(rule.getPixFee())
                        )
                        .add(
                                safe(rule.getGatewayFee())
                        )
                        .add(
                                safe(rule.getOtherFee())
                        );

        BigDecimal totalFees =
                totalFeesPercent.divide(
                        BigDecimal.valueOf(100),
                        4,
                        RoundingMode.HALF_UP
                );

        /*
         * PREÇO FINAL
         */

        BigDecimal finalPrice =
                priceWithMargin.divide(
                        BigDecimal.ONE.subtract(totalFees),
                        2,
                        RoundingMode.HALF_UP
                );

        /*
         * MARKUP MÍNIMO
         */

        BigDecimal minimumMarkupPrice =
                baseCost.multiply(
                        BigDecimal.ONE.add(
                                safe(rule.getMinimumMarkup())
                                        .divide(
                                                BigDecimal.valueOf(100),
                                                4,
                                                RoundingMode.HALF_UP
                                        )
                        )
                );

        if (
                finalPrice.compareTo(
                        minimumMarkupPrice
                ) < 0
        ) {

            finalPrice =
                    minimumMarkupPrice;
        }

        /*
         * PREÇO MÍNIMO ABSOLUTO
         */

        if (
                rule.getMinimumPrice() != null
                        &&
                        finalPrice.compareTo(
                                rule.getMinimumPrice()
                        ) < 0
        ) {

            finalPrice =
                    rule.getMinimumPrice();
        }

        /*
         * TAXAS CALCULADAS
         */

        BigDecimal marketplaceFeeValue =
                calculateFee(
                        finalPrice,
                        rule.getMarketplaceFee()
                );

        BigDecimal cardFeeValue =
                calculateFee(
                        finalPrice,
                        rule.getCardFee()
                );

        BigDecimal taxFeeValue =
                calculateFee(
                        finalPrice,
                        rule.getTaxFee()
                );

        BigDecimal pixFeeValue =
                calculateFee(
                        finalPrice,
                        rule.getPixFee()
                );

        BigDecimal gatewayFeeValue =
                calculateFee(
                        finalPrice,
                        rule.getGatewayFee()
                );

        BigDecimal otherFeeValue =
                calculateFee(
                        finalPrice,
                        rule.getOtherFee()
                );

        /*
         * LUCRO REAL
         */

        BigDecimal profitValue =
                finalPrice
                        .subtract(baseCost)
                        .subtract(marketplaceFeeValue)
                        .subtract(cardFeeValue)
                        .subtract(taxFeeValue)
                        .subtract(pixFeeValue)
                        .subtract(gatewayFeeValue)
                        .subtract(otherFeeValue);

        BigDecimal unitCost =
                baseCost.divide(
                        quantity,
                        2,
                        RoundingMode.HALF_UP
                );

        BigDecimal unitPrice =
                finalPrice.divide(
                        quantity,
                        2,
                        RoundingMode.HALF_UP
                );

        return SuggestedPriceResult.builder()

                .ruleId(rule.getId())
                .ruleName(rule.getName())

                .productionCost(productionCost)
                .baseCost(baseCost)

                .filamentCost(filamentCost)
                .machineCost(machineCost)
                .laborCost(laborCost)
                .energyCost(energyCost)

                .maintenanceCost(maintenanceCost)
                .indirectCost(indirectCost)
                .administrativeCost(administrativeCost)

                .suggestedPrice(finalPrice)

                .profitValue(profitValue)

                .marketplaceFeeValue(marketplaceFeeValue)
                .cardFeeValue(cardFeeValue)
                .taxFeeValue(taxFeeValue)
                .pixFeeValue(pixFeeValue)
                .gatewayFeeValue(gatewayFeeValue)
                .otherFeeValue(otherFeeValue)

                .totalMarginPercent(totalMarginPercent)
                .totalFeesPercent(totalFeesPercent)

                .unitCost(unitCost)
                .unitPrice(unitPrice)

                .build();
    }

    private BigDecimal calculateFee(
            BigDecimal price,
            BigDecimal percent
    ) {

        return price.multiply(
                safe(percent)
                        .divide(
                                BigDecimal.valueOf(100),
                                4,
                                RoundingMode.HALF_UP
                        )
        );
    }

    private BigDecimal calculateExtras(
            ProductPricingContext context,
            BigDecimal quantity
    ) {

        if (context.getExtraCosts() == null) {
            return BigDecimal.ZERO;
        }

        return context.getExtraCosts()
                .stream()
                .map(extra -> {

                    BigDecimal value =
                            safe(extra.getValue());

                    if (
                            extra.isMultiplyByQuantity()
                    ) {

                        return value.multiply(
                                quantity
                        );
                    }

                    return value;
                })
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }

    private BigDecimal safe(
            BigDecimal value
    ) {

        return value == null
                ? BigDecimal.ZERO
                : value;
    }
}