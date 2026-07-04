package br.com.hitbox.interfaces.dto.response.pricing;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricingVariableResponse {
    private Long id;

    private String name;

    private String type;

    private String unit;

    private Boolean required;

    private BigDecimal impactValue;

    private String impactType;
}
