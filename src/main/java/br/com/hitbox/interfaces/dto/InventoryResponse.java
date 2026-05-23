package br.com.hitbox.interfaces.dto;

import br.com.hitbox.infra.enums.InventoryUnit;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@Builder
public class InventoryResponse {

    private Long id;

    private String name;

    private Long categoriaId;

    private String category;

    private BigDecimal quantity;

    private InventoryUnit unit;

    private BigDecimal minimumStock;

    private BigDecimal cost;

    private BigDecimal unitCost;

    private String supplier;

    private String location;

    private String imageUrl;

    private Boolean active;
}
