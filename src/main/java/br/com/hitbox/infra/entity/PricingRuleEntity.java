package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pricing_rule", indexes =
@Index(name = "idx_pricing_rule_company", columnList = "company_id,name"))
@SequenceGenerator(name = "sq_pricing_rule_id", sequenceName = "seq_pricing_rule_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class PricingRuleEntity extends TenantEntity {

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
