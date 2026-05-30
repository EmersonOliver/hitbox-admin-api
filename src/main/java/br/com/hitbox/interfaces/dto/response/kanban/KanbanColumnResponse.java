package br.com.hitbox.interfaces.dto.response.kanban;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanColumnResponse {

    private Long id;

    private String columnName;

    private String columnColor;

    private Integer columnOrder;

    private Integer totalCards;

    private ServiceOrderStatus typeColumn;

    @Builder.Default
    private List<KanbanCardResponse> cards = new ArrayList<>();

}