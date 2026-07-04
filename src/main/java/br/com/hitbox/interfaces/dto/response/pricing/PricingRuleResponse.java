package br.com.hitbox.interfaces.dto.response.pricing;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PricingRuleResponse {

    private Long id;
    private String name;
    private String salesChannel;
    private BigDecimal profitMargin;
    private BigDecimal marketplaceFee;
    private BigDecimal cardFee;
    private BigDecimal minimumPrice;
    private Boolean active;
    private BigDecimal energyCostPerHour;
    private BigDecimal machineHourCost;
    private BigDecimal laborHourCost;
    private BigDecimal maintenanceRate;
    private BigDecimal indirectCost;
    private BigDecimal administrativeCost;

    private BigDecimal safetyMargin;
    private BigDecimal commercialCommission;
    private BigDecimal minimumMarkup;
    private BigDecimal lossReserve;

    private BigDecimal taxFee;
    private BigDecimal pixFee;
    private BigDecimal gatewayFee;
    private BigDecimal otherFee;
    private LocalDateTime lastUpdate;

    private List<PricingVariableResponse> variables;
}