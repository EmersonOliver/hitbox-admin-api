package br.com.hitbox.core.gateway.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCard;

import java.util.List;
import java.util.Optional;

public interface KanbanCardGateway {
    KanbanCard save(KanbanCard card);

    Optional<KanbanCard> findById(Long cardId);

    List<KanbanCard> findAll();

    void delete(Long id);

    KanbanCard update(KanbanCard domain);

    List<KanbanCard> findByServiceOrderId(Long serviceOrderId);
}
