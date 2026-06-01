package br.com.hitbox.core.usecase.kanban;

import br.com.hitbox.core.domain.aggregator.ServiceOrderStatusAggregator;
import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.core.domain.kanban.KanbanCardMovement;
import br.com.hitbox.core.gateway.ServiceOrderGateway;
import br.com.hitbox.core.gateway.kanban.KanbanCardGateway;
import br.com.hitbox.core.gateway.kanban.KanbanCardMovementGateway;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.hitbox.infra.enums.ServiceOrderStatus.*;

@Component
@RequiredArgsConstructor
public class KanbanCardMovementUseCase {

    private final KanbanCardMovementGateway gateway;
    private final ServiceOrderGateway serviceOrderGateway;
    private final ServiceOrderStatusAggregator aggregator;
    private final KanbanCardGateway cardGateway;

    public KanbanCardMovement create(
            KanbanCardMovement domain
    ) {
        validate(domain);
        domain.setMovedAt(LocalDateTime.now());
        updateServiceOrderStatus(domain.getServiceOrderId());
        return gateway.create(domain);
    }

    private void updateServiceOrderStatus(
            Long serviceOrderId
    ) {

        List<KanbanCard> cards =
                cardGateway.findByServiceOrderId(
                        serviceOrderId
                );

        ServiceOrderStatus status =
                aggregator.calculate(cards);

        serviceOrderGateway.updateStatus(
                serviceOrderId,
                status
        );
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