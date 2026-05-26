package br.com.hitbox.infra.persistence.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCardMovement;
import br.com.hitbox.core.gateway.kanban.KanbanCardMovementGateway;
import br.com.hitbox.infra.jpa.kanban.SpringDataKanbanCardMovementRepository;
import br.com.hitbox.infra.mapper.kanban.KanbanCardMovementEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class KanbanCardMovementRespositoryImpl
        implements KanbanCardMovementGateway {

    private final SpringDataKanbanCardMovementRepository jpaRepository;

    private final KanbanCardMovementEntityMapper mapper;

    @Override
    public KanbanCardMovement create(
            KanbanCardMovement domain
    ) {

        var entity = mapper.toEntity(domain);

        entity = jpaRepository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public Optional<KanbanCardMovement> findById(
            Long movementId
    ) {

        return jpaRepository.findById(movementId)
                .map(mapper::toDomain);
    }

    @Override
    public List<KanbanCardMovement> findAll() {

        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<KanbanCardMovement> findByCard(
            Long cardId
    ) {

        return jpaRepository.findByCardIdOrderByMovedAtDesc(cardId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(
            Long movementId
    ) {

        jpaRepository.deleteById(movementId);
    }
}