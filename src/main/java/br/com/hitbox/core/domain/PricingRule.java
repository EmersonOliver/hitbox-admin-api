package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.CalculationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PricingRule {
    private Long id;
    private String name;
    private String salesChannel;
    private BigDecimal profitMargin;
    private BigDecimal marketplaceFee;
    private BigDecimal cardFee;
    private BigDecimal operationalCost;
    private BigDecimal commercialCost;
    private BigDecimal minimumPrice;
    private Boolean active;
    private UUID companyId;
}
