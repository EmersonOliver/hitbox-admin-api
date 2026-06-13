package br.com.hitbox.interfaces.dto.response.inventario;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.infra.enums.StockMovementType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMovementResponse {

    private Long stockMovementId;
    private Inventory inventory;
    private StockMovementType type;
    private BigDecimal quantity;
    private BigDecimal unitCost;
    private BigDecimal totalCost;
    private String observation;
    private LocalDateTime movementDate;
}
