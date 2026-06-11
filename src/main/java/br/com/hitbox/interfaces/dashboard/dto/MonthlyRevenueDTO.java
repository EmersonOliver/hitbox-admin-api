package br.com.hitbox.interfaces.dashboard.dto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRevenueDTO {

    private Integer year;

    private Integer month;

    private BigDecimal revenue;

    private BigDecimal productionCost;

    private BigDecimal profit;
}
