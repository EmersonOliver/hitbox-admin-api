package br.com.hitbox.infra.entity;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.InventoryUnit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventario", uniqueConstraints = {
        @UniqueConstraint(name = "uk_inventario_name", columnNames = {"company_id","name"})
}, indexes = @Index(name = "idx_inventario_name_company", columnList = "company_id,name"))
@SequenceGenerator(name = "sq_inventario_id", sequenceName = "seq_inventario_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class InventoryEntity extends TenantEntity {

    @Id
    @Column(name = "inventario_id")
    @GeneratedValue(
            generator = "sq_inventario_id",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "categoria_id",
            nullable = false
    )
    private CategoriaEntity categoria;

    @Builder.Default
    @Column(
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal quantity =
            BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InventoryUnit unit;

    @Builder.Default
    @Column(
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal minimumStock =
            BigDecimal.ZERO;

    @Builder.Default
    @Column(
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal cost =
            BigDecimal.ZERO;

    @Builder.Default
    @Column(
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal unitCost =
            BigDecimal.ZERO;

    @Column
    private String supplier;

    @Column(columnDefinition = "TEXT")
    private String location;

    private String imageUrl;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Builder.Default
    @OneToMany(
            mappedBy = "inventory",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StockMovementEntity> movements =
            new ArrayList<>();

    @Formula("""
                CASE
                    WHEN minimum_stock = 0 THEN 100
                    ELSE (quantity / minimum_stock) * 100
                END
            """)
    private Double stockLevel;
}
