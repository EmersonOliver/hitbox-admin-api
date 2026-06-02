package br.com.hitbox.interfaces.dto.response.produto;

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

    private Long pricingRuleId;

    private String name;

    private Long categoriaId;

    private String categoryName;

    private String imageUrl;

    private String sku;

    private String description;

    private BigDecimal currentCalculatedCost;

    private BigDecimal currentSalePrice;

    private BigDecimal productionWeight;

    private BigDecimal shippingWeight;

    private List<ProductMaterialResponse> materials;

    private BigDecimal estimatedMinutes;
}