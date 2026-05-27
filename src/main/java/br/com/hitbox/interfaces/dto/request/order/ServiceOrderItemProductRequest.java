package br.com.hitbox.interfaces.dto.request.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrderItemProductRequest {

    private Long id;

    private Long productId;

    private BigDecimal quantity;

    private BigDecimal salePriceUnit;

    private Long estimatedMinutes;
}