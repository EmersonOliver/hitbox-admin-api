package br.com.hitbox.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Long productId;
    private Long categoriaId;
    private Long princingRuleId;
    private String imageUrl;
    private String name;
    private String sku;
    private String description;
    private String categoriaName;
    private BigDecimal currentCalculatedCost;
    private BigDecimal currentSalePrice;
    private BigDecimal previousCalculatedCost;
    private BigDecimal productionWeight;
    private BigDecimal shippingWeight;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal depth;
    private LocalDateTime calculatedAt;
    private LocalDateTime previousCalculatedAt;
    private BigDecimal estimatedMinutes;
    private UUID companyId;
    @Builder.Default
    private List<ProductMaterial> materials =
            new ArrayList<>();

    public void addComposition(ProductMaterial material) {

        materials.add(material);
    }

    public void removeComposition(ProductMaterial material) {
        materials.remove(material);
    }


    public void addImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
