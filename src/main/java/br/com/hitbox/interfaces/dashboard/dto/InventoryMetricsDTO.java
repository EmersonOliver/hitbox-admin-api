package br.com.hitbox.interfaces.dashboard.dto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMetricsDTO {
    private BigDecimal purchasedQuantity;

    private BigDecimal consumedQuantity;

    private BigDecimal currentQuantity;

    private BigDecimal minimumStock;

    private BigDecimal totalPurchasedValue;

    private BigDecimal totalConsumedValue;

    private BigDecimal averageUnitCost;

    private LocalDateTime lastEntryDate;

    private LocalDateTime lastConsumptionDate;
}
