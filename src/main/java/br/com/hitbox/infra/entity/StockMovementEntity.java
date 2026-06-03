package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.StockMovementType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movement", indexes = @Index(
        name = "idx_stock_movement_company", columnList = "company_id,inventario_id"
))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "sq_stock_movement_id", sequenceName = "seq_stock_movement_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class StockMovementEntity extends TenantEntity {
    @Id
    @Column(name = "movement_stock_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_stock_movement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "inventario_id",
            nullable = false
    )
    private InventoryEntity inventory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private StockMovementType type;

    @Column(
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal quantity;

    @Column(
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal unitCost;

    @Column(
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal totalCost;

    @Column(length = 1000)
    private String observation;

    @Column(nullable = false)
    private LocalDateTime movementDate;

    @PrePersist
    public void prePersist() {

        if (movementDate == null) {
            movementDate = LocalDateTime.now();
        }

        if (
                totalCost == null &&
                        quantity != null &&
                        unitCost != null
        ) {

            totalCost =
                    unitCost.multiply(quantity);
        }
    }
}
