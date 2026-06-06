package br.com.hitbox.core.domain.kanban;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanCardMovement {

    private Long id;

    private Long serviceOrderId;

    private Long cardId;

    private Long fromColumnId;

    private Long toColumnId;

    private LocalDateTime movedAt;
    private UUID companyId;
}