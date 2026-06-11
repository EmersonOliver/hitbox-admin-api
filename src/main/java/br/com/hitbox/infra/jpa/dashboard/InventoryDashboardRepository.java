package br.com.hitbox.infra.jpa.dashboard;

import br.com.hitbox.interfaces.dashboard.dto.InventoryMetricsDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryConsumptionDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryEntryDTO;

import java.util.List;

public interface InventoryDashboardRepository {
    InventoryMetricsDTO loadMetrics(Long inventoryId);

    List<MonthlyInventoryEntryDTO> entriesHistory(Long inventoryId);

    List<MonthlyInventoryConsumptionDTO> consumptionHistory(Long inventoryId);
}
