package br.com.hitbox.interfaces.kanban;


import br.com.hitbox.core.usecase.kanban.KanbanCardUseCase;
import br.com.hitbox.interfaces.dto.request.kanban.KanbanCardRequest;
import br.com.hitbox.interfaces.dto.response.kanban.KanbanCardResponse;
import br.com.hitbox.interfaces.mapper.kanban.KanbanCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("kanban/card")
@RequiredArgsConstructor
public class KanbanCardController {

    private final KanbanCardUseCase useCase;

    private final KanbanCardMapper mapper;

    @PostMapping("create")
    public ResponseEntity<KanbanCardResponse> createCard(
            @RequestBody KanbanCardRequest request
    ) {

        var domain = mapper.toDomain(request);
        domain.setId(!Objects.isNull(request.getId()) && request.getId().equals(0L) ?null : request.getId());
        var result = useCase.create(domain);
        var response = mapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PutMapping("edit/{cardId}")
    public ResponseEntity<KanbanCardResponse> editCard(
            @PathVariable("cardId") Long cardId,
            @RequestBody KanbanCardRequest request
    ) {
        var domain = mapper.toDomain(request);
        domain.setId(cardId);
        var result = useCase.update(domain);
        var response = mapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("find/{cardId}")
    public ResponseEntity<KanbanCardResponse> findById(
            @PathVariable("cardId") Long cardId
    ) {

        var result = useCase.findById(cardId);

        var response = mapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<KanbanCardResponse>> findAll() {

        var result = useCase.findAll();

        var response = result.stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{cardId}")
    public ResponseEntity<Void> deleteCard(
            @PathVariable("cardId") Long cardId
    ) {

        useCase.delete(cardId);

        return ResponseEntity.noContent().build();
    }
}
