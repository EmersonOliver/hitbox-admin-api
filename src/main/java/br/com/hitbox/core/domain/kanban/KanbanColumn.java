package br.com.hitbox.core.domain.kanban;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KanbanColumn {

    private Long id;

    private String columnName;

    private String columnColor;

    private Integer columnOrder;

    private Boolean initialColumn;

    private Boolean finalColumn;

    private Boolean blockedColumn;
    private ServiceOrderStatus typeColumn;
    private UUID companyId;

    @Builder.Default
    private List<KanbanCard> cards =
            new ArrayList<>();

    public void addCard(KanbanCard card) {

        cards.add(card);
    }

    public void removeCard(KanbanCard card) {

        cards.remove(card);
    }
}