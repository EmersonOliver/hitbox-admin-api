package br.com.hitbox.infra.jpa.kanban;

import br.com.hitbox.infra.entity.kanban.KanbanCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SpringDataKanbanCardRepository extends JpaRepository<KanbanCardEntity, Long> {

    @Query("select kc FROM KanbanCardEntity kc where kc.order.id =:serviceOrderId")
    List<KanbanCardEntity> findByServiceOrderId(@Param("serviceOrderId") Long serviceOrderId);
}
