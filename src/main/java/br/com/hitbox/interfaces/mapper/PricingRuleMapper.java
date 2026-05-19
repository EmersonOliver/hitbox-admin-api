package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.interfaces.dto.PricingRuleRequest;
import br.com.hitbox.interfaces.dto.PricingRuleResponse;

public class PricingRuleMapper {

    private PricingRuleMapper() {
    }

    public static PricingRule toDomain(
            PricingRuleRequest request
    ) {

        return PricingRule.builder()

                .name(request.getName())

                .categoriaId(
                        request.getCategoriaId()
                )

                .calculationType(
                        request.getCalculationType()
                )

                /*
                 * CUSTOS
                 */

                .setupCost(
                        request.getSetupCost()
                )

                .pricePerGram(
                        request.getPricePerGram()
                )

                .pricePerHour(
                        request.getPricePerHour()
                )

                .pricePerUnit(
                        request.getPricePerUnit()
                )

                .additionalCost(
                        request.getAdditionalCost()
                )

                /*
                 * LUCRO
                 */

                .profitMargin(
                        request.getProfitMargin()
                )

                /*
                 * LIMITES
                 */

                .minimumPrice(
                        request.getMinimumPrice()
                )

                /*
                 * TAXAS
                 */

                .marketplaceFee(
                        request.getMarketplaceFee()
                )

                .cardFee(
                        request.getCardFee()
                )

                /*
                 * STATUS
                 */

                .active(
                        request.getActive()
                )

                .build();
    }

    public static PricingRuleResponse toResponse(
            PricingRule domain
    ) {

        return PricingRuleResponse.builder()

                .id(domain.getId())

                /*
                 * IDENTIFICAÇÃO
                 */

                .name(domain.getName())

                .categoriaId(
                        domain.getCategoriaId()
                )

                .categoriaNome(
                        domain.getCategoria() != null
                                ? domain.getCategoria().getNome()
                                : null
                )

                /*
                 * TIPO
                 */

                .calculationType(
                        domain.getCalculationType()
                )

                /*
                 * CUSTOS
                 */

                .setupCost(
                        domain.getSetupCost()
                )

                .pricePerGram(
                        domain.getPricePerGram()
                )

                .pricePerHour(
                        domain.getPricePerHour()
                )

                .pricePerUnit(
                        domain.getPricePerUnit()
                )

                .additionalCost(
                        domain.getAdditionalCost()
                )

                /*
                 * LUCRO
                 */

                .profitMargin(
                        domain.getProfitMargin()
                )

                /*
                 * LIMITES
                 */

                .minimumPrice(
                        domain.getMinimumPrice()
                )

                /*
                 * TAXAS
                 */

                .marketplaceFee(
                        domain.getMarketplaceFee()
                )

                .cardFee(
                        domain.getCardFee()
                )

                /*
                 * STATUS
                 */

                .active(
                        domain.getActive()
                )

                .build();
    }
}