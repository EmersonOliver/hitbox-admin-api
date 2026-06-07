package br.com.hitbox.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPricingContext {

    /*
     * impressão
     */

    private BigDecimal printHours;

    private BigDecimal filamentWeight;

    private BigDecimal filamentCostPerGram;

    private BigDecimal machineHourCost;

    /*
     * custos adicionais
     */

    private BigDecimal energyCost;

    private BigDecimal packagingCost;

    /*
     * manutenção %
     */

    private BigDecimal maintenancePercentage;

    /*
     * quantidade produzida
     */

    private Integer quantity;

    /*
     * extras
     */
    private List<ProductExtraCost> extraCosts;
    private UUID companyId;
}