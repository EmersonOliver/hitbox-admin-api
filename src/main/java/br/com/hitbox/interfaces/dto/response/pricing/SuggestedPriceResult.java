package br.com.hitbox.interfaces.dto.response.pricing;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SuggestedPriceResult {

    /*
     * Regra
     */
    private Long ruleId;

    private String ruleName;

    /*
     * Custos Produção
     */

    private BigDecimal productionCost;

    private BigDecimal filamentCost;

    private BigDecimal machineCost;

    private BigDecimal laborCost;

    private BigDecimal energyCost;

    private BigDecimal extrasCost;

    /*
     * Custos Indiretos
     */

    private BigDecimal maintenanceCost;

    private BigDecimal indirectCost;

    private BigDecimal administrativeCost;

    /*
     * Resultado
     */

    private BigDecimal baseCost;

    private BigDecimal unitCost;

    private BigDecimal unitPrice;

    private BigDecimal suggestedPrice;

    /*
     * Lucro
     */

    private BigDecimal profitValue;

    private BigDecimal liquidProfit;

    /*
     * Taxas Calculadas
     */

    private BigDecimal marketplaceFeeValue;

    private BigDecimal cardFeeValue;

    private BigDecimal taxFeeValue;

    private BigDecimal pixFeeValue;

    private BigDecimal gatewayFeeValue;

    private BigDecimal otherFeeValue;

    /*
     * Totais
     */

    private BigDecimal totalMarginPercent;

    private BigDecimal totalFeesPercent;

    private BigDecimal totalFeesValue;
}