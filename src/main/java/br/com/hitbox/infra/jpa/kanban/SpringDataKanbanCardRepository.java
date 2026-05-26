package br.com.hitbox.infra.jpa.kanban;

import br.com.hitbox.infra.entity.kanban.KanbanCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataKanbanCardRepository extends JpaRepository<KanbanCardEntity, Long> {
}
