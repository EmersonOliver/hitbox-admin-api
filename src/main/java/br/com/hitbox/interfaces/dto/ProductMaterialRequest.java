package br.com.hitbox.interfaces.dto;


import br.com.hitbox.infra.enums.ConsumptionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductMaterialRequest {
    private Long inventoryId;
    private BigDecimal quantity;
    private ConsumptionType consumptionType;
}