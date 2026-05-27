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

        /*
         * QUANTIDADE
         */

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
         * MÁQUINA
         */

        BigDecimal machineCost =
                safe(context.getPrintHours())
                        .multiply(
                                safe(context.getMachineHourCost())
                        );

        /*
         * ENERGIA
         */

        BigDecimal energyCost =
                safe(context.getEnergyCost());

        /*
         * EMBALAGEM
         */

        BigDecimal packagingCost =
                safe(context.getPackagingCost());

        /*
         * EXTRAS
         */

        BigDecimal extrasCost =
                calculateExtras(
                        context,
                        quantity
                );

        /*
         * CUSTOS REGRA
         */

        BigDecimal operationalCost =
                safe(rule.getOperationalCost());

        BigDecimal commercialCost =
                safe(rule.getCommercialCost());

        /*
         * CUSTO BASE
         */

        BigDecimal baseCost =
                filamentCost
                        .add(machineCost)
                        .add(energyCost)
                        .add(packagingCost)
                        .add(extrasCost)
                        .add(operationalCost)
                        .add(commercialCost);

        /*
         * MANUTENÇÃO
         */

        BigDecimal maintenancePercentage =
                safe(context.getMaintenancePercentage());

        BigDecimal maintenanceCost =
                baseCost.multiply(
                        maintenancePercentage.divide(
                                BigDecimal.valueOf(100),
                                4,
                                RoundingMode.HALF_UP
                        )
                );

        baseCost =
                baseCost.add(maintenanceCost);

        /*
         * LUCRO
         */

        BigDecimal marginMultiplier =
                BigDecimal.ONE.add(
                        safe(rule.getProfitMargin())
                                .divide(
                                        BigDecimal.valueOf(100),
                                        4,
                                        RoundingMode.HALF_UP
                                )
                );

        BigDecimal priceWithMargin =
                baseCost.multiply(
                        marginMultiplier
                );

        /*
         * TAXAS
         */

        BigDecimal totalFees =
                safe(rule.getMarketplaceFee())
                        .add(
                                safe(rule.getCardFee())
                        )
                        .divide(
                                BigDecimal.valueOf(100),
                                4,
                                RoundingMode.HALF_UP
                        );

        BigDecimal finalPrice =
                priceWithMargin.divide(
                        BigDecimal.ONE.subtract(totalFees),
                        2,
                        RoundingMode.HALF_UP
                );

        /*
         * PREÇO MÍNIMO
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
                finalPrice.multiply(
                        safe(rule.getMarketplaceFee())
                                .divide(
                                        BigDecimal.valueOf(100),
                                        4,
                                        RoundingMode.HALF_UP
                                )
                );

        BigDecimal cardFeeValue =
                finalPrice.multiply(
                        safe(rule.getCardFee())
                                .divide(
                                        BigDecimal.valueOf(100),
                                        4,
                                        RoundingMode.HALF_UP
                                )
                );

        /*
         * LUCRO REAL
         */

        BigDecimal profitValue =
                finalPrice
                        .subtract(baseCost)
                        .subtract(marketplaceFeeValue)
                        .subtract(cardFeeValue);

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
                .salesChannel(rule.getSalesChannel())
                .productionCost(baseCost)
                .baseCost(baseCost)
                .suggestedPrice(finalPrice)
                .profitValue(profitValue)
                .marketplaceFeeValue(marketplaceFeeValue)
                .cardFeeValue(cardFeeValue)
                .machineCost(machineCost)
                .filamentCost(filamentCost)
                .maintenanceCost(maintenanceCost)
                .unitPrice(unitPrice)
                .unitCost(unitCost)

                .build();
    }

    private BigDecimal calculateExtras(
            ProductPricingContext context,
            BigDecimal quantity
    ) {
        if (
                context.getExtraCosts() == null
        ) {

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

                        return value.multiply(quantity);
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