package br.com.hitbox.infra.entity.kanban;

import br.com.hitbox.infra.entity.ItemProductEntity;
import br.com.hitbox.infra.entity.ServiceOrderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "kanban_card")
@SequenceGenerator(
        name = "sq_kanban_card_id",
        sequenceName = "seq_kanban_card_id",
        allocationSize = 1
)
public class KanbanCardEntity {

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

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KanbanCardMovementEntity> movements;


}