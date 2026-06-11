package br.com.hitbox.interfaces.dashboard.dto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyProductionDTO {
    private Integer year;

    private Integer month;

    private Long quantityProduced;
}
