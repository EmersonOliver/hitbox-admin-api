package br.com.hitbox.infra.entity;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.core.domain.ProductMaterial;
import br.com.hitbox.infra.entity.tenant.TenantEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product", indexes = @Index(
        name = "idx_product_name_company", columnList = "company_id,name,pricing_rule_id"
))
@SequenceGenerator(name = "sq_product_id", sequenceName = "seq_product_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class ProductEntity extends TenantEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_product_id"
    )
    @Column(name = "product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pricing_rule_id")
    private PricingRuleEntity pricingRule;

    private String name;

    private String imageUrl;

    private String sku;

    private String description;

    private BigDecimal currentCalculatedCost;

    private BigDecimal previousCalculatedCost;

    private BigDecimal productionWeight;

    private BigDecimal shippingWeight;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal depth;

    private LocalDateTime calculatedAt;

    private LocalDateTime previousCalculatedAt;

    private BigDecimal estimatedMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductMaterialsEntity> materials;

}
