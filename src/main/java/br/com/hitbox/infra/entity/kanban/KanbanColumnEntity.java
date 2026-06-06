package br.com.hitbox.infra.entity.kanban;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "kanban_column", indexes = {
        @Index(
                name = "idx_kanban_column_company",
                columnList = "company_id,column_order"
        )
})
@SequenceGenerator(
        name = "sq_kanban_column_id",
        sequenceName = "seq_kanban_column_id",
        allocationSize = 1
)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class KanbanColumnEntity extends TenantEntity {

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

    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus typeColumn;

    @Builder.Default
    @OneToMany(
            mappedBy = "kanbanColumn",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("cardOrder ASC")
    private List<KanbanCardEntity> cards =
            new ArrayList<>();

    @OneToMany(mappedBy = "fromColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KanbanCardMovementEntity> fromColumns;

    @OneToMany(mappedBy = "toColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KanbanCardMovementEntity> toColumns;

    private Boolean finalColumn;

    private Boolean initialColumn;

    private Boolean blockedColumn;

}