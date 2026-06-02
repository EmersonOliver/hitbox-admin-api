package br.com.hitbox.core.domain.kanban;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanCard {

    private Long id;

    private Long itemProductId;

    private Long targetColumnId;

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
    private String clientName;
    private BigDecimal quantity;

    private ServiceOrderStatus statusCard;


    @Builder.Default
    private List<KanbanCardMovement> movements =
            new ArrayList<>();

    public void move(
            Long fromColumnId,
            Long toColumnId
    ) {

        this.kanbanColumnId = toColumnId;

        movements.add(
                KanbanCardMovement.builder()
                        .cardId(this.id)
                        .fromColumnId(fromColumnId)
                        .toColumnId(toColumnId)
                        .movedAt(LocalDateTime.now())
                        .build()
        );
    }

    public void block(String reason) {

        this.blocked = true;
        this.blockedReason = reason;
    }

    public void unblock() {

        this.blocked = false;
        this.blockedReason = null;
    }

    public void updateProgress(BigDecimal progress) {

        this.productionProgress = progress;
    }


    public void startProduction() {
        this.startDatetime = LocalDateTime.now();
    }

    public void finishProduction() {
        this.finishDatetime = LocalDateTime.now();
        if (startDatetime != null) {
            this.actualMinutes =
                    Duration.between(startDatetime, finishDatetime)
                            .toMinutes();
        }
    }
}
