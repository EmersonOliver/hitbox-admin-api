package br.com.hitbox.infra.mapper.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.infra.entity.ItemProductEntity;
import br.com.hitbox.infra.entity.ServiceOrderEntity;
import br.com.hitbox.infra.entity.kanban.KanbanCardEntity;
import br.com.hitbox.infra.entity.kanban.KanbanColumnEntity;
import org.springframework.stereotype.Component;

@Component
public class KanbanCardEntityMapper {

    public KanbanCardEntity toEntity(KanbanCard domain) {

        if (domain == null) {
            return null;
        }

        return KanbanCardEntity.builder()
                .id(domain.getId())

                .item(
                        domain.getItemProductId() != null
                                ? ItemProductEntity.builder()
                                  .id(domain.getItemProductId())
                                  .build()
                                : null
                )

                .order(
                        domain.getServiceOrderId() != null
                                ? ServiceOrderEntity.builder()
                                  .id(domain.getServiceOrderId())
                                  .build()
                                : null
                )

                .kanbanColumn(
                        domain.getKanbanColumnId() != null
                                ? KanbanColumnEntity.builder()
                                  .id(domain.getKanbanColumnId())
                                  .build()
                                : null
                )

                .cardOrder(domain.getCardOrder())
                .productionProgress(domain.getProductionProgress())
                .estimatedMinutes(domain.getEstimatedMinutes())
                .actualMinutes(domain.getActualMinutes())
                .startDatetime(domain.getStartDatetime())
                .finishDatetime(domain.getFinishDatetime())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .blocked(domain.getBlocked())
                .blockedReason(domain.getBlockedReason())
                .notes(domain.getNotes())
                .quantity(domain.getQuantity())
                .build();
    }

    public KanbanCard toDomain(KanbanCardEntity entity) {

        if (entity == null) {
            return null;
        }

        return KanbanCard.builder()
                .id(entity.getId())

                .itemProductId(
                        entity.getItem() != null
                                ? entity.getItem().getId()
                                : null
                )

                .serviceOrderId(
                        entity.getOrder() != null
                                ? entity.getOrder().getId()
                                : null
                )

                .kanbanColumnId(
                        entity.getKanbanColumn() != null
                                ? entity.getKanbanColumn().getId()
                                : null
                )

                .cardOrder(entity.getCardOrder())
                .productionProgress(entity.getProductionProgress())
                .estimatedMinutes(entity.getEstimatedMinutes())
                .actualMinutes(entity.getActualMinutes())
                .startDatetime(entity.getStartDatetime())
                .finishDatetime(entity.getFinishDatetime())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .blocked(entity.getBlocked())
                .blockedReason(entity.getBlockedReason())
                .notes(entity.getNotes())
                .quantity(entity.getItem().getQuantity())
                .build();
    }
}