package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.StockMovement;
import br.com.hitbox.interfaces.dto.response.inventario.StockMovementResponse;
import org.springframework.stereotype.Component;

@Component
public class StockMovementMapper {


    public StockMovementResponse toResponse(StockMovement domain){
        return StockMovementResponse.builder()
                .stockMovementId(domain.getStockMovementId())
                .unitCost(domain.getUnitCost())
                .quantity(domain.getQuantity())
                .type(domain.getType())
                .movementDate(domain.getMovementDate())
                .observation(domain.getObservation())
                .totalCost(domain.getTotalCost())
                .inventory(domain.getInventory())
                .build();
    }



}
