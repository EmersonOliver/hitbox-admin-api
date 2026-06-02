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
@Table(name = "pricing_rule")
@SequenceGenerator(name = "sq_pricing_rule_id", sequenceName = "seq_pricing_rule_id", allocationSize = 1)
public class PricingRuleEntity {

    @Id
    @Column(name = "pricing_rule_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_pricing_rule_id"
    )
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

}
