package br.com.hitbox.infra.entity.kanban;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "kanban_card_movement")
@SequenceGenerator(
        name = "sq_kanban_card_movement_id",
        sequenceName = "seq_kanban_card_movement_id",
        allocationSize = 1
)
public class KanbanCardMovementEntity {

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
