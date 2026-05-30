package br.com.hitbox.interfaces.dto.request.kanban;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanColumnRequest {

    private Long id;

    private String columnName;

    private String columnColor;

    private Integer columnOrder;

    private ServiceOrderStatus typeColumn;

}