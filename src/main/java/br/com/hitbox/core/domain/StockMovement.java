package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.StockMovementType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMovement {

    private Long stockMovementId;
    private Inventory inventory;
    private StockMovementType type;
    private BigDecimal quantity;
    private BigDecimal unitCost;
    private BigDecimal totalCost;
    private String observation;
    private LocalDateTime movementDate;
}
