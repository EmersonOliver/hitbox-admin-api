package br.com.hitbox.interfaces.dto.request.inventario;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMovementValidateRequest {

    private Long inventoryId;
    private BigDecimal quantity;
}
