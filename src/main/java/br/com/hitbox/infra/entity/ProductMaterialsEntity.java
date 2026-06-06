package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.ConsumptionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product_material", indexes = @Index(
        name = "idx_product_materials_company", columnList = "company_id,inventario_id,product_id"
))
@SequenceGenerator(name = "sq_product_material_id", sequenceName = "seq_product_material_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class ProductMaterialsEntity extends TenantEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_product_material_id"
    )
    @Column(name = "product_material_id")
    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitCostSnapshot;

    @Enumerated(EnumType.STRING)
    private ConsumptionType consumptionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventario_id")
    private InventoryEntity inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;


}
