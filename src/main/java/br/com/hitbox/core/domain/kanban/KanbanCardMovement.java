package br.com.hitbox.core.domain.kanban;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanCardMovement {

    private Long id;

    private Long cardId;

    private Long fromColumnId;

    private Long toColumnId;

    private LocalDateTime movedAt;
}