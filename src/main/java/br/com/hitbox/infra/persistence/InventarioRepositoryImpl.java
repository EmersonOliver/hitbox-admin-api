package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.gateway.InventarioGateway;
import br.com.hitbox.infra.entity.InventoryEntity;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataInventarioRepository;
import br.com.hitbox.infra.mapper.InventarioEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventarioRepositoryImpl implements InventarioGateway {

    private final SpringDataInventarioRepository jpaRepository;

    @Override
    public Inventory salvar(Inventory domain) {
        var entity = jpaRepository.save(InventarioEntityMapper.toEntity(domain));
        return InventarioEntityMapper.toDomain(entity);
    }

    @Override
    public Inventory atualizar(Inventory domain) {
        InventoryEntity entity = this.jpaRepository.findById(domain.getId())
                .orElseThrow(() -> new HitboxException("Produto do Inventário não encontrado!"));
        InventarioEntityMapper.updateEntity(
                domain,
                entity
        );
        jpaRepository.save(entity);
        return InventarioEntityMapper.toDomain(entity);
    }

    @Override
    public InventoryEntity remover(Long id) {
        InventoryEntity entity = this.jpaRepository.findById(id)
                .orElseThrow(() -> new HitboxException("Produto do Inventário não encontrado!"));
        jpaRepository.delete(entity);
        return entity;
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }


}
