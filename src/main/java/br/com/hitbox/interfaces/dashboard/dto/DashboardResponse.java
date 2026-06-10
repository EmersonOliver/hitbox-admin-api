package br.com.hitbox.interfaces.dashboard.dto;


import br.com.hitbox.core.domain.Inventory;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DashboardResponse {

    private BigDecimal receitaTotal;
    private BigDecimal custoProducao;
    private BigDecimal lucroLiquido;
    private BigDecimal ticketMedio;

    private List<ProductScore> topProducts;
    private List<Inventory> topInventorys;
}
