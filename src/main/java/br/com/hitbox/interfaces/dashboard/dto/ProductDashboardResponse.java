package br.com.hitbox.interfaces.dashboard.dto;
import br.com.hitbox.core.domain.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDashboardResponse   {
    private Product product;

    /**
     * Produção
     */
    private Long producedQuantity;

    private Long soldQuantity;

    private Long serviceOrdersCount;

    private Long deliveredQuantity;

    /**
     * Financeiro
     */
    private BigDecimal totalRevenue;

    private BigDecimal totalProductionCost;

    private BigDecimal totalProfit;

    private BigDecimal averageMargin;

    private BigDecimal averageTicket;

    private BigDecimal profitPerHour;

    /**
     * Popularidade
     */
    private Integer ranking;

    private Integer popularityScore;

    /**
     * Histórico
     */
    private List<MonthlyProductionDTO> productionHistory;

    private List<MonthlyRevenueDTO> revenueHistory;
}
