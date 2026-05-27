package br.com.hitbox.core.gateway.kanban;

import br.com.hitbox.core.domain.kanban.KanbanColumn;

import java.util.List;
import java.util.Optional;

public interface KanbanColumnGateway {

    KanbanColumn save(KanbanColumn column);

    Optional<KanbanColumn> findById(Long id);

    List<KanbanColumn> findAll();

    void delete(Long id);

}