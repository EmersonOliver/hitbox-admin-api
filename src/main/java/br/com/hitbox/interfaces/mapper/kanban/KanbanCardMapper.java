package br.com.hitbox.interfaces.mapper.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.interfaces.dto.request.kanban.KanbanCardRequest;
import br.com.hitbox.interfaces.dto.response.kanban.KanbanCardResponse;
import org.springframework.stereotype.Component;

@Component
public class KanbanCardMapper {

    public KanbanCard toDomain(KanbanCardRequest req) {
        if (req == null) {
            return null;
        }
        return KanbanCard.builder()
                .id((req.getId() != null && req.getId().equals(0L) ? null : req.getId()))
                .itemProductId(req.getItemProductId())
                .serviceOrderId(req.getServiceOrderId())
                .kanbanColumnId(req.getKanbanColumnId())
                .quantity(req.getQuantity())
                .cardOrder(req.getCardOrder())
                .productionProgress(req.getProductionProgress())
                .estimatedMinutes(req.getEstimatedMinutes())
                .blocked(req.getBlocked())
                .blockedReason(req.getBlockedReason())
                .notes(req.getNotes())
                .build();
    }

    public KanbanCardResponse toResponse(KanbanCard domain) {

        if (domain == null) {
            return null;
        }

        return KanbanCardResponse.builder()
                .id(domain.getId())
                .itemProductId(domain.getItemProductId())
                .serviceOrderId(domain.getServiceOrderId())
                .kanbanColumnId(domain.getKanbanColumnId())
                .cardOrder(domain.getCardOrder())
                .quantity(domain.getQuantity())
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
                .productName(domain.getProductName())
                .clientName(domain.getClientName())
                .build();
    }
}