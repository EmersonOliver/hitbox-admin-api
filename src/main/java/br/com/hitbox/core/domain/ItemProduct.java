package br.com.hitbox.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemProduct {

    private Long id;

    private Long serviceOrderId;

    private Long productId;

    private Product product;

    private BigDecimal quantity;

    private BigDecimal costUnit;

    private BigDecimal totalItemCost;

    private BigDecimal salePriceUnit;

    private BigDecimal totalSalePrice;

    private Long estimatedMinutes;

    public void recalculateTotals() {

        if (quantity == null) {
            quantity = BigDecimal.ZERO;
        }

        if (costUnit == null) {
            costUnit = BigDecimal.ZERO;
        }

        if (salePriceUnit == null) {
            salePriceUnit = BigDecimal.ZERO;
        }

        this.totalItemCost =
                costUnit.multiply(quantity);

        this.totalSalePrice =
                salePriceUnit.multiply(quantity);
    }
}
