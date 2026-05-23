package br.com.hitbox.interfaces.dto;


import br.com.hitbox.infra.enums.ConsumptionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductMaterialResponse {

    private Long productMaterialId;

    private Long inventoryId;

    private String inventoryName;

    private BigDecimal quantity;

    private ConsumptionType consumptionType;

    private BigDecimal unitCostSnapshot;
}