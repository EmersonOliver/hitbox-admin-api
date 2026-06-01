package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.entity.InventoryEntity;

public class InventarioEntityMapper {

    private InventarioEntityMapper() {
    }

    public static InventoryEntity toEntity(Inventory domain) {
        if (domain == null) {
            return null;
        }
        return InventoryEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .categoria(CategoriaEntity.builder()
                        .id(domain.getCategoriaId())
                        .build())
                .quantity(domain.getQuantity())
                .unit(domain.getUnit())
                .minimumStock(domain.getMinimumStock())
                .cost(domain.getCost())
                .unitCost(domain.getUnitCost())
                .supplier(domain.getSupplier())
                .location(domain.getLocation())
                .imageUrl(domain.getImageUrl())
                .active(domain.getActive())
                .unitCost(domain.getUnitCost())
                .build();
    }

    public static Inventory toDomain(InventoryEntity entity) {
        if (entity == null) {
            return null;
        }
        var inventory  = Inventory.builder()
                .id(entity.getId())
                .name(entity.getName())
                .categoriaId(entity.getCategoria() != null
                        ? entity.getCategoria().getId()
                        : null)
                .categoria(entity.getCategoria() != null
                        ? CategoriaEntityMapper.toDomain(entity.getCategoria())
                        : null)
                .quantity(entity.getQuantity())
                .unit(entity.getUnit())
                .minimumStock(entity.getMinimumStock())
                .cost(entity.getCost())
                .unitCost(entity.getUnitCost())
                .supplier(entity.getSupplier())
                .location(entity.getLocation())
                .imageUrl(entity.getImageUrl())
                .active(entity.getActive())
                .build();
        StockMovementEntityMapper stockMapper = new StockMovementEntityMapper();
        var movementsEntity = entity.getMovements();

        movementsEntity.forEach(item-> {
            inventory.getMovements().add(stockMapper.toDomain(item));
        });
        return inventory;
    }
    public static Inventory toDomainPage(InventoryEntity entity) {
        if (entity == null) {
            return null;
        }
        var inventory = Inventory.builder()
                .id(entity.getId())
                .name(entity.getName())
                .categoriaId(entity.getCategoria() != null
                        ? entity.getCategoria().getId()
                        : null)
                .categoria(entity.getCategoria() != null
                        ? CategoriaEntityMapper.toDomain(entity.getCategoria())
                        : null)
                .quantity(entity.getQuantity())
                .unit(entity.getUnit())
                .minimumStock(entity.getMinimumStock())
                .cost(entity.getCost())
                .unitCost(entity.getUnitCost())
                .supplier(entity.getSupplier())
                .location(entity.getLocation())
                .imageUrl(entity.getImageUrl())
                .active(entity.getActive())
                .build();
        StockMovementEntityMapper stockMapper = new StockMovementEntityMapper();
        var movementsDomain = entity.getMovements().stream().map(stockMapper::toDomain).toList();
        inventory.setMovements(movementsDomain);
        return inventory;
    }

    public static void updateEntity(Inventory domain,
                                    InventoryEntity entity) {
        if (domain == null || entity == null) {
            return;
        }
        entity.setName(domain.getName());
        entity.setCategoria(CategoriaEntity.builder()
                        .id(domain.getCategoriaId())
                        .build());
        entity.setUnit(domain.getUnit());
        entity.setMinimumStock(domain.getMinimumStock());
        entity.setSupplier(domain.getSupplier());
        entity.setLocation(domain.getLocation());
        entity.setImageUrl(domain.getImageUrl());
        entity.setActive(domain.getActive());
        if(!domain.getMovements().isEmpty()){
            entity.setQuantity(domain.getQuantity());
            entity.setUnitCost(domain.getUnitCost());
            entity.setCost(domain.getCost());
            entity.setUnitCost(domain.getUnitCost());
        }

    }
}