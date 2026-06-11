package br.com.hitbox.core.usecase;

import br.com.hitbox.infra.query.InventoryDashboardQueryService;
import br.com.hitbox.interfaces.dashboard.dto.InventoryDashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class InventoryDashboardUseCase {

    private final InventoryDashboardQueryService queryService;

    public InventoryDashboardResponse dashboard(
            Long inventoryId
    ) {

        var inventory =
                queryService.findInventory(inventoryId);

        var metrics =
                queryService.loadMetrics(inventoryId);

        var entries =
                queryService.entriesHistory(inventoryId);

        var consumptions =
                queryService.consumptionHistory(inventoryId);

        BigDecimal currentStockValue =
                metrics.getCurrentQuantity()
                        .multiply(
                                metrics.getAverageUnitCost()
                        );

        BigDecimal stockCoveragePercent =
                BigDecimal.ZERO;

        Integer daysWithoutEntry =
                calculateDaysWithoutEntry(
                        metrics.getLastEntryDate()
                );

        Integer daysWithoutConsumption =
                calculateDaysWithoutConsumption(
                        metrics.getLastConsumptionDate()
                );

        Boolean belowMinimumStock =
                calculateBelowMinimumStock(
                        metrics.getCurrentQuantity(),
                        metrics.getMinimumStock()
                );

        if (
                metrics.getPurchasedQuantity() != null
                        &&
                        metrics.getPurchasedQuantity()
                                .compareTo(BigDecimal.ZERO) > 0
        ) {

            stockCoveragePercent =
                    metrics.getCurrentQuantity()
                            .multiply(
                                    BigDecimal.valueOf(100)
                            )
                            .divide(
                                    metrics.getPurchasedQuantity(),
                                    2,
                                    RoundingMode.HALF_UP
                            );
        }

        return InventoryDashboardResponse.builder()

                .inventory(inventory)

                .purchasedQuantity(
                        metrics.getPurchasedQuantity()
                )

                .consumedQuantity(
                        metrics.getConsumedQuantity()
                )

                .currentQuantity(
                        metrics.getCurrentQuantity()
                )

                .minimumStock(
                        metrics.getMinimumStock()
                )

                .stockCoveragePercent(
                        stockCoveragePercent
                )

                .totalPurchasedValue(
                        metrics.getTotalPurchasedValue()
                )

                .totalConsumedValue(
                        metrics.getTotalConsumedValue()
                )

                .currentStockValue(
                        currentStockValue
                )

                .averageUnitCost(
                        metrics.getAverageUnitCost()
                )

                .lastEntryDate(
                        metrics.getLastEntryDate()
                )

                .lastConsumptionDate(
                        metrics.getLastConsumptionDate()
                )
                .daysWithoutEntry(
                        daysWithoutEntry
                )

                .daysWithoutConsumption(
                        daysWithoutConsumption
                )

                .belowMinimumStock(
                        belowMinimumStock
                )
                .entriesHistory(entries)

                .consumptionHistory(consumptions)

                .build();
    }

    private Integer calculateDaysWithoutEntry(
            LocalDateTime lastEntryDate
    ) {

        if (lastEntryDate == null) {
            return 0;
        }

        return (int)
                ChronoUnit.DAYS.between(
                        lastEntryDate.toLocalDate(),
                        LocalDate.now()
                );
    }

    private Integer calculateDaysWithoutConsumption(
            LocalDateTime lastConsumptionDate
    ) {

        if (lastConsumptionDate == null) {
            return 0;
        }

        return (int)
                ChronoUnit.DAYS.between(
                        lastConsumptionDate.toLocalDate(),
                        LocalDate.now()
                );
    }

    private Boolean calculateBelowMinimumStock(
            BigDecimal currentQuantity,
            BigDecimal minimumStock
    ) {

        if (
                currentQuantity == null
                        ||
                        minimumStock == null
        ) {

            return false;
        }

        return currentQuantity.compareTo(
                minimumStock
        ) < 0;
    }
}
