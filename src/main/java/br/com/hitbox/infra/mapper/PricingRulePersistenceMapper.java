package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Categoria;
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

                /*
                 * IDENTIFICAÇÃO
                 */

                .name(entity.getName())

                .categoriaId(
                        entity.getCategoria() != null
                                ? entity.getCategoria().getId()
                                : null
                )

                .categoria(
                        entity.getCategoria() != null
                                ? Categoria.builder()
                                  .id(entity.getCategoria().getId())
                                  .nome(entity.getCategoria().getNome())
                                  .build()
                                : null
                )

                /*
                 * TIPO
                 */

                .calculationType(
                        entity.getCalculationType()
                )

                /*
                 * CUSTOS
                 */

                .setupCost(
                        entity.getSetupCost()
                )

                .pricePerGram(
                        entity.getPricePerGram()
                )

                .pricePerHour(
                        entity.getPricePerHour()
                )

                .pricePerUnit(
                        entity.getPricePerUnit()
                )

                .additionalCost(
                        entity.getAdditionalCost()
                )

                /*
                 * LUCRO
                 */

                .profitMargin(
                        entity.getProfitMargin()
                )

                /*
                 * LIMITES
                 */

                .minimumPrice(
                        entity.getMinimumPrice()
                )

                /*
                 * TAXAS
                 */

                .marketplaceFee(
                        entity.getMarketplaceFee()
                )

                .cardFee(
                        entity.getCardFee()
                )

                /*
                 * STATUS
                 */

                .active(
                        entity.getActive()
                )

                .build();
    }

    public static PricingRuleEntity toEntity(
            PricingRule domain
    ) {

        return PricingRuleEntity.builder()

                .id(domain.getId())

                .name(domain.getName())

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