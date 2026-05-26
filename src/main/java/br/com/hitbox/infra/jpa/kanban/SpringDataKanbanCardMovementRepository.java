package br.com.hitbox.infra.jpa.kanban;

import br.com.hitbox.infra.entity.kanban.KanbanCardMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataKanbanCardMovementRepository extends JpaRepository<KanbanCardMovementEntity, Long> {

    List<KanbanCardMovementEntity>
    findByCardIdOrderByMovedAtDesc(
            Long cardId
    );
}
