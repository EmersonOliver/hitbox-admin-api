package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.infra.jpa.SpringDataInventarioRepository;
import br.com.hitbox.infra.mapper.InventarioEntityMapper;
import br.com.hitbox.interfaces.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventarioQueryService {

    private final SpringDataInventarioRepository repository;


    public Page<Inventory> page(Pageable pageable) {
        return repository.findAll(pageable).map(InventarioEntityMapper::toDomain);
    }
}
