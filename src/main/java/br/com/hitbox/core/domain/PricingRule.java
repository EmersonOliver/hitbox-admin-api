package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.CalculationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PricingRule {
    private Long id;

    /*
     * IDENTIFICAÇÃO
     */

    private String name;

    private Long categoriaId;

    private Categoria categoria;

    /*
     * TIPO DE CÁLCULO
     */

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
