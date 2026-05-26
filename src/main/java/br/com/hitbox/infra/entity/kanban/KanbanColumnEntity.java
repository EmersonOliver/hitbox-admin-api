package br.com.hitbox.infra.entity.kanban;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "kanban_column")
@SequenceGenerator(
        name = "sq_kanban_column_id",
        sequenceName = "seq_kanban_column_id",
        allocationSize = 1
)
public class KanbanColumnEntity {

    @Id
    @Column(name = "kanban_column_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_kanban_column_id"
    )
    private Long id;

    @Column(nullable = false, length = 120)
    private String columnName;

    @Column(nullable = false, length = 20)
    private String columnColor;

    @Column(nullable = false)
    private Integer columnOrder;

    @Builder.Default
    @OneToMany(
            mappedBy = "kanbanColumn",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("cardOrder ASC")
    private List<KanbanCardEntity> cards =
            new ArrayList<>();

    private Boolean finalColumn;

    private Boolean initialColumn;

    private Boolean blockedColumn;
}