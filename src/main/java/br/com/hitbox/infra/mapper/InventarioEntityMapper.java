package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.entity.InventoryEntity;

public class InventarioEntityMapper {


    public static InventoryEntity toEntity(Inventory domain){
        return InventoryEntity.builder()
                .id(domain.getId())
                .cost(domain.getCost())
                .name(domain.getName())
                .active(domain.getActive())
                .imageUrl(domain.getImageUrl())
                .location(domain.getLocation())
                .unit(domain.getUnit())
                .categoria(CategoriaEntity.builder().id(domain.getCategoriaId()).build())
                .minimumStock(domain.getMinimumStock())
                .quantity(domain.getQuantity())
                .supplier(domain.getSupplier())
                .build();
    }

    public static Inventory toDomain(InventoryEntity entity){
        return Inventory.builder()
                .id(entity.getId())
                .cost(entity.getCost())
                .name(entity.getName())
                .categoria(CategoriaEntityMapper.toDomain(entity.getCategoria()))
                .active(entity.getActive())
                .imageUrl(entity.getImageUrl())
                .location(entity.getLocation())
                .unit(entity.getUnit())
                .categoriaId(entity.getCategoria().getId())
                .minimumStock(entity.getMinimumStock())
                .quantity(entity.getQuantity())
                .supplier(entity.getSupplier())
                .build();
    }

    public static void updateEntity(
            Inventory domain,
            InventoryEntity entity
    ) {

        entity.setName(
                domain.getName()
        );

        entity.setCategoria(
                CategoriaEntity.builder()
                        .id(
                                domain.getCategoriaId()
                        )
                        .build()
        );

        entity.setQuantity(
                domain.getQuantity()
        );

        entity.setUnit(
                domain.getUnit()
        );

        entity.setMinimumStock(
                domain.getMinimumStock()
        );

        entity.setCost(
                domain.getCost()
        );

        entity.setSupplier(
                domain.getSupplier()
        );

        entity.setLocation(
                domain.getLocation()
        );

        entity.setImageUrl(
                domain.getImageUrl()
        );

        entity.setActive(
                domain.getActive()
        );
    }


}
