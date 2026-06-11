package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.gateway.InventarioGateway;
import br.com.hitbox.infra.jpa.dashboard.InventoryDashboardRepository;
import br.com.hitbox.interfaces.dashboard.dto.InventoryMetricsDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryConsumptionDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryDashboardQueryService {
    private final InventarioGateway inventoryGateway;

    private final InventoryDashboardRepository repository;

    public Inventory findInventory(Long inventoryId) {
        return inventoryGateway
                .findById(inventoryId);

    }

    public InventoryMetricsDTO loadMetrics(Long inventoryId) {

        return repository.loadMetrics(inventoryId);
    }

    public List<MonthlyInventoryEntryDTO> entriesHistory(
            Long inventoryId
    ) {

        return repository.entriesHistory(inventoryId);
    }

    public List<MonthlyInventoryConsumptionDTO> consumptionHistory(
            Long inventoryId
    ) {

        return repository.consumptionHistory(inventoryId);
    }
}
