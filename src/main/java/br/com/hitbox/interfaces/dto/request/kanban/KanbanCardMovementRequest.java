package br.com.hitbox.interfaces.dto.request.kanban;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanCardMovementRequest {

    private Long id;

    private Long serviceOrderId;

    private Long cardId;

    private Long fromColumnId;

    private Long toColumnId;

}