package br.com.hitbox.interfaces.dashboard.dto;

import br.com.hitbox.core.domain.Inventory;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDashboardResponse {

    private Inventory inventory;

    /**
     * Estoque
     */
    private BigDecimal purchasedQuantity;

    private BigDecimal consumedQuantity;

    private BigDecimal currentQuantity;

    private BigDecimal minimumStock;

    private BigDecimal stockCoveragePercent;

    private Integer daysWithoutEntry;
    private Integer daysWithoutConsumption;
    private Boolean belowMinimumStock;

    /**
     * Financeiro
     */
    private BigDecimal totalPurchasedValue;

    private BigDecimal totalConsumedValue;

    private BigDecimal currentStockValue;

    private BigDecimal averageUnitCost;

    /**
     * Datas
     */
    private LocalDateTime lastEntryDate;

    private LocalDateTime lastConsumptionDate;

    /**
     * Histórico
     */
    private List<MonthlyInventoryEntryDTO> entriesHistory;

    private List<MonthlyInventoryConsumptionDTO> consumptionHistory;
}
