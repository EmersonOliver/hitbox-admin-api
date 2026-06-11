package br.com.hitbox.infra.persistence.kanban;

import br.com.hitbox.core.domain.kanban.KanbanCard;
import br.com.hitbox.core.gateway.kanban.KanbanCardGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.kanban.SpringDataKanbanCardRepository;
import br.com.hitbox.infra.mapper.kanban.KanbanCardEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Slf4j
public class KanbanCardRepositoryImpl implements KanbanCardGateway {

    private final SpringDataKanbanCardRepository jpaRepository;
    private final KanbanCardEntityMapper mapper;

    @Override
    public KanbanCard save(KanbanCard card) {
        var entity = mapper.toEntity(card);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<KanbanCard> findById(Long cardId) {
        return jpaRepository.findById(cardId).map(mapper::toDomain);
    }

    @Override
    public List<KanbanCard> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Long id) {
        var entity = this.jpaRepository.findById(id)
                .orElseThrow(() ->
                        new HitboxException("Entity not found")
                );
        jpaRepository.delete(entity);
    }

    @Override
    public KanbanCard update(KanbanCard domain) {
        var entity = mapper.toEntity(domain);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<KanbanCard> findByServiceOrderId(Long serviceOrderId) {
        return jpaRepository.findByServiceOrderId(serviceOrderId).stream().map(mapper::toDomain).toList();
    }
}
