package br.com.hitbox.infra.entity;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.infra.enums.InventoryUnit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventario", uniqueConstraints = {
        @UniqueConstraint(name = "uk_inventario_name", columnNames = "name")
})
@SequenceGenerator(name = "sq_inventario_id", sequenceName = "seq_inventario_id", allocationSize = 1)
public class InventoryEntity {

    @Id
    @Column(name = "inventario_id")
    @GeneratedValue(generator = "sq_inventario_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "categoria_id",
            nullable = false
    )
    private CategoriaEntity categoria;

    @Column(
            precision = 19,
            scale = 4
    )
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    private InventoryUnit unit;

    @Column(
            precision = 19,
            scale = 4,
            nullable = false
    )
    private BigDecimal  minimumStock;

    private BigDecimal cost;
    private String supplier;

    @Column(columnDefinition = "TEXT")
    private String location;
    private String imageUrl;

    @Column(nullable = false)
    private Boolean active = true;

    @Formula("""
CASE
    WHEN minimum_stock = 0 THEN 100
    ELSE (quantity / minimum_stock) * 100
END
""")
    private Double stockLevel;

}
