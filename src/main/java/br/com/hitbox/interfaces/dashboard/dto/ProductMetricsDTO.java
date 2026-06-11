package br.com.hitbox.interfaces.dashboard.dto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMetricsDTO {
    private Long producedQuantity;

    private Long soldQuantity;

    private Long serviceOrdersCount;

    private BigDecimal totalRevenue;

    private BigDecimal totalProductionCost;

    private BigDecimal totalMinutes;
}
