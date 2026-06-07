package br.com.hitbox.infra.entity.kanban;

import br.com.hitbox.infra.entity.ItemProductEntity;
import br.com.hitbox.infra.entity.ServiceOrderEntity;
import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "kanban_card", indexes = @Index(
        name = "idx_kanban_card_company", columnList = "company_id,item_product_id,service_order_id,kanban_column_id"
))
@SequenceGenerator(
        name = "sq_kanban_card_id",
        sequenceName = "seq_kanban_card_id",
        allocationSize = 1
)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class KanbanCardEntity extends TenantEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_kanban_card_id"
    )
    @Column(name = "kanban_card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_product_id")
    private ItemProductEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id")
    private ServiceOrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kanban_column_id")
    private KanbanColumnEntity kanbanColumn;

    private Integer cardOrder;

    @Column(precision = 5, scale = 2)
    private BigDecimal productionProgress;

    private BigDecimal estimatedMinutes;

    private Long actualMinutes;

    private LocalDateTime startDatetime;

    private LocalDateTime finishDatetime;

    private Boolean blocked;

    @Column(length = 1000)
    private String blockedReason;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private BigDecimal productionQuantity;

    private Long completedQuantity;

    private Long failedQuantity;

    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus statusCard;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KanbanCardMovementEntity> movements;

}