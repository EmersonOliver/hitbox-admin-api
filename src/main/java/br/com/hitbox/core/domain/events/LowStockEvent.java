package br.com.hitbox.core.domain.events;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class LowStockEvent {

    private UUID companyId;
    private Long inventoryId;
    private String inventoryName;
    private String categoryName;
    private Double currentQuantity;
    private Double minimumQuantity;
    private String unit;
    private LocalDateTime occurredAt;
}
