package br.com.hitbox.interfaces.dto.request.pricing;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PricingRuleRequest {
    private String name;

    private Boolean active;

    private BigDecimal minimumPrice;

    /*
     * Custos Operacionais
     */
    private BigDecimal energyCostPerHour;
    private BigDecimal machineHourCost;
    private BigDecimal laborHourCost;
    private BigDecimal maintenanceRate;
    private BigDecimal indirectCost;
    private BigDecimal administrativeCost;

    /*
     * Margens
     */
    private BigDecimal profitMargin;
    private BigDecimal safetyMargin;
    private BigDecimal commercialCommission;
    private BigDecimal minimumMarkup;
    private BigDecimal lossReserve;

    /*
     * Taxas
     */
    private BigDecimal marketplaceFee;
    private BigDecimal cardFee;
    private BigDecimal taxFee;
    private BigDecimal pixFee;
    private BigDecimal gatewayFee;
    private BigDecimal otherFee;

    /*
     * Variáveis
     */
    private List<PricingVariableRequest> variables;

}
