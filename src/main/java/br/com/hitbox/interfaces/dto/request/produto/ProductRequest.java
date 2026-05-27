package br.com.hitbox.interfaces.dto.request.produto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String name;
    private String imageUrl;
    private String description;
    private Long categoryId;
    private BigDecimal productionWeight;
    private BigDecimal shippingWeight;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal depth;
    private List<ProductMaterialRequest> materials;
    private BigDecimal estimatedMinutes;
}
