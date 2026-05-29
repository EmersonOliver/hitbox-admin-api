package br.com.hitbox.interfaces.dto.response.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrderItemProductResponse {

    private Long id;

    private Long productId;

    private String productName;

    private String productImage;

    private BigDecimal quantity;

    private BigDecimal costUnit;

    private BigDecimal totalItemCost;

    private BigDecimal salePriceUnit;

    private BigDecimal totalSalePrice;

    private BigDecimal estimatedMinutes;
}