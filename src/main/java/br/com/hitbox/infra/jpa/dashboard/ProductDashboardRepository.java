package br.com.hitbox.infra.jpa.dashboard;

import br.com.hitbox.interfaces.dashboard.dto.MonthlyProductionDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyRevenueDTO;
import br.com.hitbox.interfaces.dashboard.dto.ProductMetricsDTO;

import java.util.List;

public interface ProductDashboardRepository {
    ProductMetricsDTO loadMetrics(Long productId);

    List<MonthlyProductionDTO> productionHistory(Long productId);

    List<MonthlyRevenueDTO> revenueHistory(Long productId);

    Integer ranking(Long productId);

    Long countRankedProducts();
}
