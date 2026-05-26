package br.com.hitbox.infra.mapper.kanban;

import br.com.hitbox.core.domain.kanban.KanbanColumn;
import br.com.hitbox.infra.entity.kanban.KanbanColumnEntity;
import org.springframework.stereotype.Component;

@Component
public class KanbanColumnEntityMapper {

    public KanbanColumnEntity toEntity(KanbanColumn domain) {

        if (domain == null) {
            return null;
        }

        return KanbanColumnEntity.builder()
                .id(domain.getId())
                .columnName(domain.getColumnName())
                .columnColor(domain.getColumnColor())
                .columnOrder(domain.getColumnOrder())
                .build();
    }

    public KanbanColumn toDomain(KanbanColumnEntity entity) {

        if (entity == null) {
            return null;
        }

        return KanbanColumn.builder()
                .id(entity.getId())
                .columnName(entity.getColumnName())
                .columnColor(entity.getColumnColor())
                .columnOrder(entity.getColumnOrder())
                .build();
    }
}