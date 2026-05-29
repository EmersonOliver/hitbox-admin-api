package br.com.hitbox.interfaces.mapper.kanban;

import br.com.hitbox.core.domain.kanban.KanbanColumn;
import br.com.hitbox.interfaces.dto.request.kanban.KanbanColumnRequest;
import br.com.hitbox.interfaces.dto.response.kanban.KanbanColumnResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KanbanColumnMapper {

    private final KanbanCardMapper cardMapper;

    public KanbanColumn toDomain(KanbanColumnRequest req) {

        if (req == null) {
            return null;
        }

        return KanbanColumn.builder()
                .id(req.getId())
                .columnName(req.getColumnName())
                .columnColor(req.getColumnColor())
                .columnOrder(req.getColumnOrder())
                .build();
    }

    public KanbanColumnResponse toResponse(KanbanColumn domain) {

        if (domain == null) {
            return null;
        }

        return KanbanColumnResponse.builder()
                .id(domain.getId())
                .columnName(domain.getColumnName())
                .columnColor(domain.getColumnColor())
                .columnOrder(domain.getColumnOrder())
                .cards(domain.getCards().stream().map(cardMapper::toResponse).toList())
                .build();
    }
}