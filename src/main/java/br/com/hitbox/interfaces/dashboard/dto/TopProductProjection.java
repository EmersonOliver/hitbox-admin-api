package br.com.hitbox.interfaces.dashboard.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TopProductProjection {
    private Long productId;
    private String productName;
    private Long totalOrders;
}
