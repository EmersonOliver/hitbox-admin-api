package br.com.hitbox.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricingVariable {
    private Long id;

    private String name;

    private String type;

    private String unit;

    private Boolean required;

    private BigDecimal impactValue;

    private String impactType;
}
