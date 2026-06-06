package br.com.hitbox.infra.mapper.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.core.domain.kanban.KanbanColumn;
import br.com.hitbox.infra.entity.kanban.KanbanColumnEntity;
import org.springframework.stereotype.Component;

import java.util.List;

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
                .typeColumn(domain.getTypeColumn())
                .companyId(domain.getCompanyId())
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
                .typeColumn(entity.getTypeColumn())
                .cards(
                        entity.getCards().stream().map(rs ->
                                KanbanCard.builder()
                                        .id(rs.getId())
                                        .serviceOrderId(rs.getOrder().getId())
                                        .kanbanColumnId(rs.getKanbanColumn().getId())
                                        .createdAt(rs.getCreatedAt())
                                        .blocked(rs.getBlocked())
                                        .actualMinutes(rs.getActualMinutes())
                                        .blockedReason(rs.getBlockedReason())
                                        .estimatedMinutes(rs.getEstimatedMinutes())
                                        .cardOrder(rs.getCardOrder())
                                        .finishDatetime(rs.getFinishDatetime())
                                        .itemProductId(rs.getItem().getId())
                                        .updatedAt(rs.getUpdatedAt())
                                        .productionProgress(rs.getProductionProgress())
                                        .clientName(rs.getOrder().getCliente().getNome())
                                        .productName(rs.getItem().getProduct().getName())
                                        .quantity(rs.getQuantity())
                                        .notes(rs.getNotes())
                                        .build()
                        ).toList()
                )
                .companyId(entity.getCompanyId())
                .build();
    }
}