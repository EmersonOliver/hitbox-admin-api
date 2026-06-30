package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
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

    private BigDecimal profitMargin;

    private BigDecimal marketplaceFee;

    private BigDecimal cardFee;

    private BigDecimal minimumPrice;

    private Boolean active;
    @OneToMany(
            mappedBy = "pricingRule",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<PricingVariableEntity> variables =
            new ArrayList<>();

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

}
