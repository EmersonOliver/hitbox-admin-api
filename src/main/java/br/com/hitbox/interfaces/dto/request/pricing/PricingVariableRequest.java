package br.com.hitbox.interfaces.dto.request.pricing;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PricingVariableRequest {

    private String name;

    private String type;

    private String unit;

    private Boolean required;

    private BigDecimal impactValue;

    private String impactType;
}
