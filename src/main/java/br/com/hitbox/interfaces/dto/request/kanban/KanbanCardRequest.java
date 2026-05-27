package br.com.hitbox.interfaces.dto.request.kanban;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanCardRequest {

    private Long id;

    private Long itemProductId;

    private Long serviceOrderId;

    private Long kanbanColumnId;

    private Integer cardOrder;

    private BigDecimal productionProgress;

    private Long estimatedMinutes;

    private Boolean blocked;

    private String blockedReason;

    private String notes;

}