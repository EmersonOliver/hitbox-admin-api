package br.com.hitbox.interfaces.dto.response.order;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrderResponse {

    private Long id;

    private UUID clienteId;

    private String clienteNome;

    private List<ServiceOrderItemProductResponse> items;

    private ServiceOrderStatus status;

    private BigDecimal totalSalePrice;

    private BigDecimal totalProfit;

    private Long totalEstimatedMinutes;

    private LocalDateTime createdAt;

    private LocalDateTime expectedDate;

    private LocalDateTime finishedAt;

    private String observations;
}