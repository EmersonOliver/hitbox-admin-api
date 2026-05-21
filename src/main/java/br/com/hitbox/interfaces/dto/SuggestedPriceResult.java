package br.com.hitbox.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SuggestedPriceResult {
    private Long ruleId;
    private String ruleName;
    private String salesChannel;
    private BigDecimal productionCost;
    private BigDecimal baseCost;
    private BigDecimal filamentCost;
    private BigDecimal machineCost;
    private BigDecimal maintenanceCost;
    private BigDecimal extrasCost;
    private BigDecimal unitCost;
    private BigDecimal unitPrice;
    private BigDecimal suggestedPrice;
    private BigDecimal profitValue;
    private BigDecimal marketplaceFeeValue;
    private BigDecimal cardFeeValue;
    private BigDecimal marginPercentage;
    private BigDecimal feePercentage;
    private BigDecimal totalFeesValue;
    private BigDecimal liquidProfit;

}
