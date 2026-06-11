package br.com.hitbox.core.usecase.kanban;

import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.domain.ServiceOrder;
import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.core.gateway.ProductGateway;
import br.com.hitbox.core.gateway.ServiceOrderGateway;
import br.com.hitbox.core.gateway.kanban.KanbanCardGateway;
import br.com.hitbox.core.gateway.kanban.KanbanCardMovementGateway;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KanbanCardUseCase {

    private final KanbanCardGateway gateway;
    private final ServiceOrderGateway serviceOrderGateway;
    private final KanbanCardMovementGateway movementGateway;
    private final ProductGateway productGateway;

    public KanbanCard create(KanbanCard card) {
        validDomainCard(card);
        return gateway.save(card);
    }

    private void validDomainCard(KanbanCard card) {
        var order = serviceOrderGateway.findById(card.getServiceOrderId())
                .orElseThrow(() -> new HitboxException("Ordem de serviço não encontrada!"));

        var itemProduct = order.getItems().stream().filter(item ->
                        item.getId().equals(card.getItemProductId()))
                .findFirst().orElseThrow(() -> new HitboxException("Item não encontrado!"));

        var product = productGateway.findById(itemProduct.getProductId())
                .orElseThrow(() -> new HitboxException("Produto não encontrado!"));

        card.setCreatedAt(LocalDateTime.now());
        card.setProductName(product.getName());
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
        KanbanCard findedCard = gateway.findById(id).orElseThrow(() -> new HitboxException("Card não encontrado!"));
        ServiceOrder order = serviceOrderGateway.findById(findedCard.getServiceOrderId()).orElseThrow(() -> new HitboxException("Ordem de serviço não encontrada"));
        order.setStatus(ServiceOrderStatus.OPEN);
        serviceOrderGateway.update(order);
        gateway.delete(id);
    }

    public KanbanCard update(KanbanCard domain) {
        var movements = movementGateway.findByCard(domain.getId());
        domain.setMovements(movements);
        verifyCardExists(domain);

        return gateway.update(domain);
    }

    private void verifyCardExists(KanbanCard domain) {
        var card = gateway.findById(domain.getId())
                .orElseThrow(() -> new HitboxException("Card não localizado!"));
        domain.setCreatedAt(card.getCreatedAt());
        domain.setUpdatedAt(card.getUpdatedAt());
    }

    public KanbanCard findById(Long cardId) {
        return gateway.findById(cardId).orElseThrow(() -> new HitboxException("Produto em risco!"));
    }
}