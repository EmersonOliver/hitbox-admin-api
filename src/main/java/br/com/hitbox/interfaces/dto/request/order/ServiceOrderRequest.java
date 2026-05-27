package br.com.hitbox.interfaces.dto.request.order;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrderRequest {

    private Long id;

    private UUID clienteId;

    private List<ServiceOrderItemProductRequest> items;

    private ServiceOrderStatus status;

    private LocalDateTime expectedDate;

    private String observations;
}