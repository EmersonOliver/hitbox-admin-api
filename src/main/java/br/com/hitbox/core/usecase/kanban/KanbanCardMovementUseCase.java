package br.com.hitbox.core.usecase.kanban;

import br.com.hitbox.core.aggregator.ServiceOrderStatusAggregator;
import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.core.domain.kanban.KanbanCardMovement;
import br.com.hitbox.core.gateway.InventarioGateway;
import br.com.hitbox.core.gateway.ProductGateway;
import br.com.hitbox.core.gateway.ServiceOrderGateway;
import br.com.hitbox.core.gateway.kanban.KanbanCardGateway;
import br.com.hitbox.core.gateway.kanban.KanbanCardMovementGateway;
import br.com.hitbox.core.gateway.kanban.KanbanColumnGateway;
import br.com.hitbox.core.usecase.StockMovementUseCase;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import br.com.hitbox.infra.enums.StockMovementType;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KanbanCardMovementUseCase {

    private final KanbanCardMovementGateway gateway;
    private final KanbanColumnGateway columnGateway;
    private final ServiceOrderGateway serviceOrderGateway;
    private final ServiceOrderStatusAggregator aggregator;
    private final KanbanCardGateway cardGateway;
    private final InventarioGateway inventarioGateway;

    private final StockMovementUseCase stockMovementUseCase;
    private final ProductGateway productGateway;

    public KanbanCardMovement create(
            KanbanCardMovement domain
    ) {
        validate(domain);
        domain.setMovedAt(LocalDateTime.now());
        updateServiceOrderStatus(domain);

        return gateway.create(domain);
    }

    private void updateServiceOrderStatus(
            KanbanCardMovement domain
    ) {
        Long serviceOrderId = domain.getServiceOrderId();
        var toColumn = columnGateway.findById(domain.getToColumnId()).orElseThrow(() -> new HitboxException("Para coluna não encontrado!"));
        List<KanbanCard> cards =
                cardGateway.findByServiceOrderId(
                        serviceOrderId
                );

        cards.stream()
                .filter(card ->
                        card.getId().equals(domain.getCardId()))
                .findFirst()
                .ifPresent(card -> {
                    card.setKanbanColumnId(
                            toColumn.getId()
                    );
                    card.setStatusCard(
                            toColumn.getTypeColumn()
                    );
                });
        ServiceOrderStatus status =
                aggregator.calculate(cards);

        serviceOrderGateway.updateStatus(
                serviceOrderId,
                status
        );

        movementStock(cards, status);
    }

    private void movementStock(List<KanbanCard> cards, ServiceOrderStatus status) {
//        if (status.equals(ServiceOrderStatus.DELIVERED)) {
        for (KanbanCard c : cards) {

            if (c.getStatusCard().equals(ServiceOrderStatus.DELIVERED)) {
                var serviceOrder = serviceOrderGateway.findById(c.getServiceOrderId())
                        .orElseThrow(() -> new HitboxException("Ordem de Serviço não encontrada!"));

                var productByServiceOrder = serviceOrder.getItems().stream()
                        .map(i ->
                                productGateway.findById(i.getProductId()).orElseThrow(() -> new HitboxException("Produto não encontrado pelo id"))
                        ).toList();

                productByServiceOrder.forEach(item -> {
                    var materialsInventory =
                            item.getMaterials();

                    for (var material : materialsInventory) {

                        var inventory = material.getInventory();
                        var quantity = material.getQuantity().multiply(c.getQuantity());
                        var movementType = StockMovementType.PRODUCTION_CONSUMPTION;
                        var cost = material.getUnitCostSnapshot();
                        stockMovementUseCase.movimentar(
                                inventory.getId(),
                                movementType,
                                quantity,
                                cost,
                                "Saída de produção do item " + item.getName());
                    }
                });
            }
        }
//        }
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