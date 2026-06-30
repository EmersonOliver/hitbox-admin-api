package br.com.hitbox.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pricing_variable")
@SequenceGenerator(name = "sq_pricing_variable_id",
        sequenceName = "seq_pricing_variable_id",
        allocationSize = 1)
public class PricingVariableEntity {

    @Id
    @Column(name = "pricing_variable_id")
    @GeneratedValue(generator = "sq_pricing_variable_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String type;

    private String unit;

    private Boolean required;

    private BigDecimal impactValue;

    private String impactType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "pricing_rule_id",
            nullable = false
    )
    private PricingRuleEntity pricingRule;
}
