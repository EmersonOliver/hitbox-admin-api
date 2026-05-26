package br.com.hitbox.infra.persistence.kanban;

import br.com.hitbox.core.domain.kanban.KanbanColumn;
import br.com.hitbox.core.gateway.kanban.KanbanColumnGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.kanban.SpringDataKanbanColumnRepository;
import br.com.hitbox.infra.mapper.kanban.KanbanColumnEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class KanbanColumnRepositoryImpl implements KanbanColumnGateway {

    private final SpringDataKanbanColumnRepository jpaRepository;
    private final KanbanColumnEntityMapper mapper;

    @Override
    public KanbanColumn save(KanbanColumn column) {
        var entity = mapper.toEntity(column);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<KanbanColumn> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<KanbanColumn> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Long id) {
        var entity = jpaRepository.findById(id)
                .orElseThrow(() -> new HitboxException("Ocorreu um erro ao buscar cliente"));
        jpaRepository.delete(entity);
    }
}
