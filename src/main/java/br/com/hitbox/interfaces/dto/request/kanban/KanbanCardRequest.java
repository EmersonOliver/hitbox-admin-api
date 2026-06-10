package br.com.hitbox.interfaces.dto.request.kanban;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    private BigDecimal quantity;

    private BigDecimal productionProgress;

    private BigDecimal estimatedMinutes;

    private Boolean blocked;

    private String blockedReason;

    private String notes;

    private ServiceOrderStatus status;


}