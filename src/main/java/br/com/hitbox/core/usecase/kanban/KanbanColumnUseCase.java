package br.com.hitbox.core.usecase.kanban;

import br.com.hitbox.core.domain.kanban.KanbanColumn;
import br.com.hitbox.core.gateway.kanban.KanbanColumnGateway;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KanbanColumnUseCase {

    private final KanbanColumnGateway gateway;

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

        gateway.delete(id);
    }

    public KanbanColumn findById(Long columnId) {
        return gateway.findById(columnId).orElseThrow(() -> new HitboxException("Coluna não encontrada"));
    }
}