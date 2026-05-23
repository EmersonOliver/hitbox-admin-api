package br.com.hitbox.interfaces.dto;

import br.com.hitbox.infra.enums.StockMovementType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMovementRequest {
    private Long inventoryId;
    private StockMovementType type;
    private BigDecimal quantity;
    private BigDecimal totalCost;
    private String observation;
}
