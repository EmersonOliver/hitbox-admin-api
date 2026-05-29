package br.com.hitbox.interfaces.dto.response.kanban;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanCardResponse {

    private Long id;

    private Long itemProductId;

    private Long serviceOrderId;

    private Long kanbanColumnId;

    private Integer cardOrder;

    private BigDecimal productionProgress;

    private BigDecimal estimatedMinutes;

    private Long actualMinutes;

    private Boolean blocked;

    private String blockedReason;

    private String notes;

    private LocalDateTime startDatetime;

    private LocalDateTime finishDatetime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String productName;

    private String productImage;

    private String clientName;

    private BigDecimal quantity;

}