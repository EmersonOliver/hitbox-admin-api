package br.com.hitbox.infra.jpa.kanban;

import br.com.hitbox.infra.entity.kanban.KanbanColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataKanbanColumnRepository extends JpaRepository<KanbanColumnEntity, Long> {
}
