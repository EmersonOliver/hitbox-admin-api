package br.com.hitbox.infra.entity.kanban;

import br.com.hitbox.infra.entity.ItemProductEntity;
import br.com.hitbox.infra.entity.ServiceOrderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    /**
     * Produto da OS
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_product_id")
    private ItemProductEntity item;

    /**
     * Ordem de serviço principal
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id")
    private ServiceOrderEntity order;

    /**
     * Coluna atual do Kanban
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kanban_column_id")
    private KanbanColumnEntity kanbanColumn;

    /**
     * Ordem visual do card dentro da coluna
     */
    private Integer cardOrder;

    /**
     * Progresso manual
     */
    @Column(precision = 5, scale = 2)
    private BigDecimal productionProgress;

    /**
     * Tempo previsto em minutos
     */
    private Long estimatedMinutes;

    /**
     * Tempo real gasto
     */
    private Long actualMinutes;

    /**
     * Início produção
     */
    private LocalDateTime startDatetime;

    /**
     * Finalização produção
     */
    private LocalDateTime finishDatetime;

    /**
     * Card bloqueado?
     */
    private Boolean blocked;

    /**
     * Motivo do bloqueio
     */
    @Column(length = 1000)
    private String blockedReason;

    /**
     * Observações operacionais
     */
    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Long productionQuantity;

    private Long completedQuantity;

    private Long failedQuantity;

}