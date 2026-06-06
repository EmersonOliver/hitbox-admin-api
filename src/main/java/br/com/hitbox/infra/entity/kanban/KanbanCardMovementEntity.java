package br.com.hitbox.infra.entity.kanban;


import br.com.hitbox.infra.entity.tenant.TenantEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "kanban_card_movement", indexes = @Index(
        name = "idx_kanban_card_movement_company",
        columnList = "company_id, kanban_card_id, from_column_id,to_column_id"
))
@SequenceGenerator(
        name = "sq_kanban_card_movement_id",
        sequenceName = "seq_kanban_card_movement_id",
        allocationSize = 1
)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class KanbanCardMovementEntity extends TenantEntity {

    @Id
    @Column(name = "kanban_card_movement_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_kanban_card_movement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kanban_card_id")
    private KanbanCardEntity card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_column_id")
    private KanbanColumnEntity fromColumn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_column_id")
    private KanbanColumnEntity toColumn;

    private LocalDateTime movedAt;

}
