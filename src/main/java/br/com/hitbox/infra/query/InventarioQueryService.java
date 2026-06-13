package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.domain.StockMovement;
import br.com.hitbox.infra.entity.InventoryEntity;
import br.com.hitbox.infra.entity.StockMovementEntity;
import br.com.hitbox.infra.jpa.SpringDataInventarioRepository;
import br.com.hitbox.infra.jpa.SpringDataStockMovement;
import br.com.hitbox.infra.jpa.specification.InventorySpecification;
import br.com.hitbox.infra.mapper.InventarioEntityMapper;
import br.com.hitbox.infra.mapper.StockMovementEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioQueryService {

    private final SpringDataInventarioRepository repository;
    private final SpringDataStockMovement springDataStockMovement;
    private final StockMovementEntityMapper stockMovementEntityMapper;


    public Page<Inventory> page(Pageable pageable, List<Long> categoriasIds, String search) {
        Specification<InventoryEntity> specification =
                InventorySpecification.byCategorias(
                        categoriasIds,
                        search
                );
        return repository.findAll(specification, pageable).map(InventarioEntityMapper::toDomainPage);
    }

    public Long countAll() {
        return repository.count();
    }

    public Page<Inventory> listTopInventory(Pageable pageable) {
        return repository.findAll(pageable).map(InventarioEntityMapper::toDomain);
    }

    public Page<StockMovement> findMovements(Long inventoryId, Pageable pageable) {
        Specification<StockMovementEntity> specs =
                (root, query, builder) -> {
                    return builder.equal(root.get("inventory").get("id"), inventoryId);
                };
        return springDataStockMovement.findAll(specs, pageable).map(stockMovementEntityMapper::toDomain);
    }
}
