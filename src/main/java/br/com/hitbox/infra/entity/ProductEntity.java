package br.com.hitbox.infra.entity;

import br.com.hitbox.core.domain.ProductMaterial;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
@SequenceGenerator(name = "sq_product_id", sequenceName = "seq_product_id", allocationSize = 1)
public class ProductEntity {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_product_id"
    )
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String imageUrl;

    private String sku;

    private String description;

    private BigDecimal currentCalculatedCost;

    private BigDecimal previousCalculatedCost;

    private BigDecimal productionWeight;

    private BigDecimal shippingWeight;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal depth;

    private LocalDateTime calculatedAt;

    private LocalDateTime previousCalculatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductMaterialsEntity> materials;
}
