package br.com.hitbox.interfaces.dto.produto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;

    private String name;

    private Long categoriaId;

    private String categoryName;

    private String imageUrl;

    private String sku;

    private String description;

    private BigDecimal currentCalculatedCost;

    private BigDecimal productionWeight;

    private BigDecimal shippingWeight;

    private List<ProductMaterialResponse> materials;
}