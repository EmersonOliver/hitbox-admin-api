package br.com.hitbox.core.gateway.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCardMovement;

import java.util.List;
import java.util.Optional;

public interface KanbanCardMovementGateway {
    KanbanCardMovement create(KanbanCardMovement domain);

    Optional<KanbanCardMovement> findById(Long movementId);

    List<KanbanCardMovement> findAll();

    List<KanbanCardMovement> findByCard(Long cardId);

    void delete(Long movementId);
}
