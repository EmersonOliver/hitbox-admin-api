package br.com.hitbox.core.usecase.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.core.gateway.kanban.KanbanCardGateway;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KanbanCardUseCase {

    private final KanbanCardGateway gateway;

    public KanbanCard create(KanbanCard card) {
        return gateway.save(card);
    }

    public KanbanCard move(
            Long cardId,
            Long fromColumnId,
            Long toColumnId
    ) {

        var card =
                gateway.findById(cardId)
                        .orElseThrow();

        card.move(fromColumnId, toColumnId);

        return gateway.save(card);
    }

    public KanbanCard updateProgress(
            Long cardId,
            BigDecimal progress
    ) {

        var card =
                gateway.findById(cardId)
                        .orElseThrow();

        card.updateProgress(progress);

        return gateway.save(card);
    }

    public KanbanCard block(
            Long cardId,
            String reason
    ) {

        var card =
                gateway.findById(cardId)
                        .orElseThrow();

        card.block(reason);

        return gateway.save(card);
    }

    public KanbanCard unblock(Long cardId) {

        var card =
                gateway.findById(cardId)
                        .orElseThrow();

        card.unblock();

        return gateway.save(card);
    }

    public List<KanbanCard> findAll() {

        return gateway.findAll();
    }

    public void delete(Long id) {

        gateway.delete(id);
    }

    public KanbanCard update(KanbanCard domain) {
    return gateway.update(domain);
    }

    public KanbanCard findById(Long cardId) {
        return gateway.findById(cardId).orElseThrow(()-> new HitboxException("Produto em risco!"));
    }
}