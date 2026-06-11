package br.com.hitbox.core.aggregator;

import org.springframework.stereotype.Component;
import br.com.hitbox.core.domain.Product;
import br.com.hitbox.interfaces.dashboard.dto.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
@Component
public class ProductDashboardAggregate {
    public ProductDashboardResponse aggregate(
            Product product,
            ProductMetricsDTO metrics,
            Integer ranking,
            Integer popularityScore,
            List<MonthlyProductionDTO> productionHistory,
            List<MonthlyRevenueDTO> revenueHistory
    ) {

        BigDecimal revenue =
                defaultValue(metrics.getTotalRevenue());

        BigDecimal productionCost =
                defaultValue(metrics.getTotalProductionCost());

        BigDecimal profit =
                revenue.subtract(productionCost);

        BigDecimal averageMargin =
                calculateMargin(
                        revenue,
                        profit
                );

        BigDecimal averageTicket =
                calculateTicket(
                        revenue,
                        metrics.getServiceOrdersCount()
                );

        BigDecimal profitPerHour =
                calculateProfitPerHour(
                        profit,
                        metrics.getTotalMinutes()
                );

        return ProductDashboardResponse.builder()
                .product(product)
                .soldQuantity(0L)
                .producedQuantity(
                        metrics.getProducedQuantity()
                )

                .soldQuantity(
                        metrics.getSoldQuantity()
                )

                .serviceOrdersCount(
                        metrics.getServiceOrdersCount()
                )

                .totalRevenue(revenue)

                .totalProductionCost(productionCost)

                .totalProfit(profit)

                .averageMargin(averageMargin)

                .averageTicket(averageTicket)

                .profitPerHour(profitPerHour)

                .ranking(ranking)

                .popularityScore(popularityScore)

                .productionHistory(productionHistory)

                .revenueHistory(revenueHistory)

                .build();
    }

    private BigDecimal calculateMargin(
            BigDecimal revenue,
            BigDecimal profit
    ) {

        if (revenue.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return profit
                .multiply(BigDecimal.valueOf(100))
                .divide(
                        revenue,
                        2,
                        RoundingMode.HALF_UP
                );
    }

    private BigDecimal calculateTicket(
            BigDecimal revenue,
            Long serviceOrders
    ) {

        if (serviceOrders == null ||
                serviceOrders == 0) {

            return BigDecimal.ZERO;
        }

        return revenue.divide(
                BigDecimal.valueOf(serviceOrders),
                2,
                RoundingMode.HALF_UP
        );
    }

    private BigDecimal calculateProfitPerHour(
            BigDecimal profit,
            BigDecimal totalMinutes
    ) {

        if (totalMinutes == null ||
                totalMinutes.compareTo(BigDecimal.ZERO) == 0) {

            return BigDecimal.ZERO;
        }

        BigDecimal hours =
                totalMinutes.divide(
                        BigDecimal.valueOf(60),
                        2,
                        RoundingMode.HALF_UP
                );

        return profit.divide(
                hours,
                2,
                RoundingMode.HALF_UP
        );
    }

    private BigDecimal defaultValue(
            BigDecimal value
    ) {

        return value == null
                ? BigDecimal.ZERO
                : value;
    }
}
