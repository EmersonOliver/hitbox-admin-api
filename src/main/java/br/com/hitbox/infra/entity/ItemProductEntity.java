package br.com.hitbox.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "item_product")
@SequenceGenerator(
        name = "sq_item_product_id",
        sequenceName = "seq_item_product_id",
        allocationSize = 1
)
public class ItemProductEntity {

    @Id
    @Column(name = "item_product_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_item_product_id"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "service_order_id",
            nullable = false
    )
    private ServiceOrderEntity serviceOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private ProductEntity product;

    @Column(nullable = false, precision = 19, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal costUnit;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalItemCost;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal salePriceUnit;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalSalePrice;

    private BigDecimal estimatedMinutes;
}