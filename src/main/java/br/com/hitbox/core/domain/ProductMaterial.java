package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.ConsumptionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductMaterial {

    private Long productMaterialId;
    private Inventory inventory;
    private BigDecimal quantity;
    private ConsumptionType consumptionType;
    private BigDecimal unitCostSnapshot;
}
