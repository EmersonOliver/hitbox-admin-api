package br.com.hitbox.core.usecase.kanban;

import br.com.hitbox.core.domain.kanban.KanbanColumn;
import br.com.hitbox.core.gateway.ServiceOrderGateway;
import br.com.hitbox.core.gateway.kanban.KanbanColumnGateway;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KanbanColumnUseCase {

    private final KanbanColumnGateway gateway;
    private final ServiceOrderGateway serviceOrderGateway;

    public KanbanColumn create(KanbanColumn column) {
        return gateway.save(column);
    }

    public List<KanbanColumn> findAll() {
        return gateway.findAll();
    }

    public KanbanColumn update(KanbanColumn column) {
        return gateway.save(column);
    }

    public void delete(Long id) {
        var column = gateway.findById(id).orElseThrow(()-> new HitboxException("Ocorreu um erro!"));
        var cards = column.getCards();
        for(var card: cards){
            var order = serviceOrderGateway.findById(card.getServiceOrderId());
            if(order.isEmpty()){
                continue;
            }
            var domainOrder = order.get();
            domainOrder.setStatus(ServiceOrderStatus.OPEN);
            serviceOrderGateway.update(domainOrder);
        }
        gateway.delete(id);
    }

    public KanbanColumn findById(Long columnId) {
        return gateway.findById(columnId).orElseThrow(() -> new HitboxException("Coluna não encontrada"));
    }
}