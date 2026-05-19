package br.com.hitbox.interfaces.dto;

import br.com.hitbox.infra.enums.InventoryUnit;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InventoryRequest {
    private String name;
    private Long categoriaId;
    private BigDecimal quantity;
    private InventoryUnit unit;
    private BigDecimal minimumStock;
    private BigDecimal cost;
    private String supplier;
    private String location;
    private Boolean active;
}
