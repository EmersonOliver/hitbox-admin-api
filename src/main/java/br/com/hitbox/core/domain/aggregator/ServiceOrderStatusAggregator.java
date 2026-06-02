package br.com.hitbox.core.domain.aggregator;

import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceOrderStatusAggregator {

    public ServiceOrderStatus calculate(
            List<KanbanCard> cards
    ) {

        if (cards == null || cards.isEmpty()) {
            return ServiceOrderStatus.OPEN;
        }

        if (cards.stream()
                .allMatch(c ->
                        c.getStatusCard() == ServiceOrderStatus.CANCELED)) {

            return ServiceOrderStatus.CANCELED;
        }

        if (cards.stream()
                .allMatch(c ->
                        c.getStatusCard() == ServiceOrderStatus.DELIVERED)) {

            return ServiceOrderStatus.DELIVERED;
        }

        if (cards.stream()
                .allMatch(c ->
                        c.getStatusCard() == ServiceOrderStatus.FINISHED)) {

            return ServiceOrderStatus.FINISHED;
        }

        boolean hasFinished =
                cards.stream()
                        .anyMatch(c ->
                                c.getStatusCard() == ServiceOrderStatus.FINISHED);

        boolean hasNotFinished =
                cards.stream()
                        .anyMatch(c ->
                                c.getStatusCard() != ServiceOrderStatus.FINISHED);

        if (hasFinished && hasNotFinished) {

            return ServiceOrderStatus.PARTIALLY_FINISHED;
        }

        if (cards.stream()
                .anyMatch(c ->
                        c.getStatusCard() == ServiceOrderStatus.IN_PRODUCTION)) {

            return ServiceOrderStatus.IN_PRODUCTION;
        }

        return ServiceOrderStatus.OPEN;
    }
}
