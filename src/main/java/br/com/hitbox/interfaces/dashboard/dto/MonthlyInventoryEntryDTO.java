package br.com.hitbox.interfaces.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyInventoryEntryDTO {

    private Integer year;

    private Integer month;

    private BigDecimal quantity;

    private BigDecimal value;
}
