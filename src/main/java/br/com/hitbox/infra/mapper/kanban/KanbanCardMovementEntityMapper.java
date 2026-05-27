package br.com.hitbox.infra.mapper.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCardMovement;
import br.com.hitbox.infra.entity.kanban.KanbanCardEntity;
import br.com.hitbox.infra.entity.kanban.KanbanCardMovementEntity;
import br.com.hitbox.infra.entity.kanban.KanbanColumnEntity;
import org.springframework.stereotype.Component;

@Component
public class KanbanCardMovementEntityMapper {

    public KanbanCardMovementEntity toEntity(KanbanCardMovement domain) {

        if (domain == null) {
            return null;
        }

        return KanbanCardMovementEntity.builder()
                .id(domain.getId())

                .card(
                        domain.getCardId() != null
                                ? KanbanCardEntity.builder()
                                  .id(domain.getCardId())
                                  .build()
                                : null
                )

                .fromColumn(
                        domain.getFromColumnId() != null
                                ? KanbanColumnEntity.builder()
                                  .id(domain.getFromColumnId())
                                  .build()
                                : null
                )

                .toColumn(
                        domain.getToColumnId() != null
                                ? KanbanColumnEntity.builder()
                                  .id(domain.getToColumnId())
                                  .build()
                                : null
                )

                .movedAt(domain.getMovedAt())
                .build();
    }

    public KanbanCardMovement toDomain(KanbanCardMovementEntity entity) {

        if (entity == null) {
            return null;
        }

        return KanbanCardMovement.builder()
                .id(entity.getId())

                .cardId(
                        entity.getCard() != null
                                ? entity.getCard().getId()
                                : null
                )

                .fromColumnId(
                        entity.getFromColumn() != null
                                ? entity.getFromColumn().getId()
                                : null
                )

                .toColumnId(
                        entity.getToColumn() != null
                                ? entity.getToColumn().getId()
                                : null
                )

                .movedAt(entity.getMovedAt())
                .build();
    }
}