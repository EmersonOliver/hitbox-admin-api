package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.StockMovement;
import br.com.hitbox.infra.entity.InventoryEntity;
import br.com.hitbox.infra.entity.StockMovementEntity;
import org.springframework.stereotype.Component;

@Component
public class StockMovementEntityMapper {

    public StockMovementEntity toEntity(
            StockMovement domain
    ) {

        if (domain == null) {
            return null;
        }

        return StockMovementEntity.builder()
                .id(
                        domain.getStockMovementId()
                )
                .inventory(
                        domain.getInventory() != null
                                ? InventoryEntity.builder()
                                  .id(domain.getInventory().getId())
                                  .build()
                                : null
                )
                .type(
                        domain.getType()
                )
                .quantity(
                        domain.getQuantity()
                )
                .unitCost(
                        domain.getUnitCost()
                )
                .totalCost(
                        domain.getTotalCost()
                )
                .observation(
                        domain.getObservation()
                )
                .movementDate(
                        domain.getMovementDate()
                )
                .build();
    }

    public StockMovement toDomain(
            StockMovementEntity entity
    ) {

        if (entity == null) {
            return null;
        }

        return StockMovement.builder()
                .stockMovementId(
                        entity.getId()
                )
                .inventory(
                        entity.getInventory() != null
                                ? br.com.hitbox.core.domain.Inventory.builder()
                                  .id(entity.getInventory().getId())
                                  .name(entity.getInventory().getName())
                                  .build()
                                : null
                )
                .type(
                        entity.getType()
                )
                .quantity(
                        entity.getQuantity()
                )
                .unitCost(
                        entity.getUnitCost()
                )
                .totalCost(
                        entity.getTotalCost()
                )
                .observation(
                        entity.getObservation()
                )
                .movementDate(
                        entity.getMovementDate()
                )
                .companyId(entity.getCompanyId())
                .build();
    }

    public void updateEntity(
            StockMovement domain,
            StockMovementEntity entity
    ) {

        entity.setType(
                domain.getType()
        );

        entity.setQuantity(
                domain.getQuantity()
        );

        entity.setUnitCost(
                domain.getUnitCost()
        );

        entity.setTotalCost(
                domain.getTotalCost()
        );

        entity.setObservation(
                domain.getObservation()
        );

        entity.setMovementDate(
                domain.getMovementDate()
        );

        if (domain.getInventory() != null) {

            entity.setInventory(
                    InventoryEntity.builder()
                            .id(domain.getInventory().getId())
                            .build()
            );
        }
    }
}
