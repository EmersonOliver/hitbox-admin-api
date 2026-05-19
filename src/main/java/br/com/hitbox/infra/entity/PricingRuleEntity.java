package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.enums.CalculationType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pricing_rule")
@SequenceGenerator(name = "sq_pricing_rule_id", sequenceName = "seq_pricing_rule_id", allocationSize = 1)
public class PricingRuleEntity {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_pricing_rule_id"
    )
    private Long id;

    /*
     * IDENTIFICAÇÃO
     */

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    /*
     * TIPO DE CÁLCULO
     */

    @Enumerated(EnumType.STRING)
    private CalculationType calculationType;

    /*
     * CUSTOS
     */

    private BigDecimal setupCost;

    private BigDecimal pricePerGram;

    private BigDecimal pricePerHour;

    private BigDecimal pricePerUnit;

    private BigDecimal additionalCost;

    /*
     * LUCRO
     */

    private BigDecimal profitMargin;

    /*
     * LIMITES
     */

    private BigDecimal minimumPrice;

    /*
     * TAXAS
     */

    private BigDecimal marketplaceFee;

    private BigDecimal cardFee;

    /*
     * STATUS
     */

    private Boolean active;
}
