package br.com.hitbox.core.usecase.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCardMovement;
import br.com.hitbox.core.gateway.kanban.KanbanCardMovementGateway;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KanbanCardMovementUseCase {

    private final KanbanCardMovementGateway gateway;

    public KanbanCardMovement create(
            KanbanCardMovement domain
    ) {

        validate(domain);

        domain.setMovedAt(LocalDateTime.now());

        return gateway.create(domain);
    }

    public KanbanCardMovement findById(
            Long movementId
    ) {

        return gateway.findById(movementId)
                .orElseThrow(() ->
                        new HitboxException(
                                "Movimentação do card não encontrada!"
                        )
                );
    }

    public List<KanbanCardMovement> findAll() {

        return gateway.findAll();
    }

    public List<KanbanCardMovement> findByCard(
            Long cardId
    ) {

        return gateway.findByCard(cardId);
    }

    public void delete(
            Long movementId
    ) {

        findById(movementId);

        gateway.delete(movementId);
    }

    private void validate(
            KanbanCardMovement domain
    ) {

        if (domain == null) {

            throw new HitboxException(
                    "Movimentação inválida!"
            );
        }

        if (domain.getCardId() == null) {

            throw new HitboxException(
                    "Card é obrigatório!"
            );
        }

        if (domain.getToColumnId() == null) {

            throw new HitboxException(
                    "Coluna destino é obrigatória!"
            );
        }
    }
}