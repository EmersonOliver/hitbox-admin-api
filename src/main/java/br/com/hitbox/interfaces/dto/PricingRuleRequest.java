package br.com.hitbox.interfaces.dto;

import br.com.hitbox.infra.enums.CalculationType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PricingRuleRequest {
    private String name;

    private Long categoriaId;

    private CalculationType calculationType;

    /*
     * CUSTOS
     */

    private BigDecimal setupCost;

    private BigDecimal pricePerGram;

    private BigDecimal pricePerHour;

    private BigDecimal pricePerUnit;

    private BigDecimal additionalCost;

    /*
     * LUCRO
     */

    private BigDecimal profitMargin;

    /*
     * LIMITES
     */

    private BigDecimal minimumPrice;

    /*
     * TAXAS
     */

    private BigDecimal marketplaceFee;

    private BigDecimal cardFee;

    /*
     * STATUS
     */

    private Boolean active;
}
