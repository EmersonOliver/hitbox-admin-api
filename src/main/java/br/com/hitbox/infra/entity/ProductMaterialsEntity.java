package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.enums.ConsumptionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_material")
@SequenceGenerator(name = "sq_product_material_id", sequenceName = "seq_product_material_id", allocationSize = 1)
public class ProductMaterialsEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_product_material_id"
    )
    @Column(name = "product_material_id")
    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitCostSnapshot;

    @Enumerated(EnumType.STRING)
    private ConsumptionType consumptionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventario_id")
    private InventoryEntity inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;


}
