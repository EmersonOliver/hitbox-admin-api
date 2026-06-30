package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.CalculationType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricingRule {
    private Long id;

    private UUID companyId;

    private String name;

    private Boolean active;

    private BigDecimal minimumPrice;

    /*
     * Custos
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

    private List<PricingVariable> variables;
}
