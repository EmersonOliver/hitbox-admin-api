package br.com.hitbox.interfaces.dto.response.kanban;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanCardMovementResponse {

    private Long id;

    private Long serviceOrderId;

    private Long cardId;

    private Long fromColumnId;

    private Long toColumnId;

    private String fromColumnName;

    private String toColumnName;

    private LocalDateTime movedAt;

}