package br.com.hitbox.interfaces.mapper.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCardMovement;
import br.com.hitbox.interfaces.dto.request.kanban.KanbanCardMovementRequest;
import br.com.hitbox.interfaces.dto.response.kanban.KanbanCardMovementResponse;
import org.springframework.stereotype.Component;

@Component
public class KanbanCardMovementMapper {

    public KanbanCardMovement toDomain(KanbanCardMovementRequest req) {
        if (req == null) {
            return null;
        }
        return KanbanCardMovement.builder()
                .id(req.getId())
                .cardId(req.getCardId())
                .serviceOrderId(req.getServiceOrderId())
                .fromColumnId(req.getFromColumnId())
                .toColumnId(req.getToColumnId())
                .build();
    }

    public KanbanCardMovementResponse toResponse(KanbanCardMovement domain) {

        if (domain == null) {
            return null;
        }

        return KanbanCardMovementResponse.builder()
                .id(domain.getId())
                .cardId(domain.getCardId())
                .serviceOrderId(domain.getServiceOrderId())
                .fromColumnId(domain.getFromColumnId())
                .toColumnId(domain.getToColumnId())
                .movedAt(domain.getMovedAt())
                .build();
    }
}