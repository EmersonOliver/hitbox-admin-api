package br.com.hitbox.interfaces.dto;

import br.com.hitbox.infra.enums.CalculationType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PricingRuleRequest {
    private String name;
    private String salesChannel;
    private BigDecimal profitMargin;
    private BigDecimal marketplaceFee;
    private BigDecimal cardFee;
    private BigDecimal operationalCost;
    private BigDecimal commercialCost;
    private BigDecimal minimumPrice;
    private Boolean active;

}
