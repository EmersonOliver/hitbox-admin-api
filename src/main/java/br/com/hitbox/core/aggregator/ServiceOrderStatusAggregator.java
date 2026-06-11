package br.com.hitbox.core.aggregator;

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
        boolean hasFinished =
                cards.stream()
                        .anyMatch(c ->
                                c.getStatusCard() ==
                                        ServiceOrderStatus.FINISHED);

        boolean hasCanceled =
                cards.stream()
                        .anyMatch(c ->
                                c.getStatusCard() ==
                                        ServiceOrderStatus.CANCELED);

        boolean hasOpen =
                cards.stream()
                        .anyMatch(c ->
                                c.getStatusCard() ==
                                        ServiceOrderStatus.OPEN);

        boolean hasInProduction =
                cards.stream()
                        .anyMatch(c ->
                                c.getStatusCard() ==
                                        ServiceOrderStatus.IN_PRODUCTION);

        if (cards.stream()
                .allMatch(c ->
                        c.getStatusCard() ==
                                ServiceOrderStatus.CANCELED)) {

            return ServiceOrderStatus.CANCELED;
        }
        if (cards.stream()
                .allMatch(c ->
                        c.getStatusCard() ==
                                ServiceOrderStatus.FINISHED)) {

            return ServiceOrderStatus.FINISHED;
        }

        if (cards.stream()
                .allMatch(c ->
                        c.getStatusCard() ==
                                ServiceOrderStatus.DELIVERED)) {

            return ServiceOrderStatus.DELIVERED;
        }

        if (cards.stream().allMatch(c -> c.getStatusCard() == ServiceOrderStatus.CANCELED)) {
            return ServiceOrderStatus.CANCELED;
        }

        if ((hasOpen && hasInProduction) || (!hasOpen && hasInProduction)) {
            return ServiceOrderStatus.IN_PRODUCTION;
        }
        if ((hasOpen && hasFinished) || (!hasOpen && hasFinished)) {
            return ServiceOrderStatus.FINISHED;
        }
        if((hasOpen && hasCanceled) || (!hasOpen && hasCanceled)){
            return ServiceOrderStatus.PARTIALLY_FINISHED;
        }
        return ServiceOrderStatus.OPEN;
    }
}
