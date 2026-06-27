package br.com.hitbox.interfaces.kanban;

import br.com.hitbox.core.usecase.kanban.KanbanCardMovementUseCase;
import br.com.hitbox.interfaces.dto.request.kanban.KanbanCardMovementRequest;
import br.com.hitbox.interfaces.dto.response.kanban.KanbanCardMovementResponse;
import br.com.hitbox.interfaces.mapper.kanban.KanbanCardMovementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kanban/card-movement")
@RequiredArgsConstructor
public class KanbanCardMovementController {

    private final KanbanCardMovementUseCase useCase;

    private final KanbanCardMovementMapper mapper;

    @PreAuthorize("hasAuthority('PRODUCTION_CREATE')")
    @PostMapping("create")
    public ResponseEntity<KanbanCardMovementResponse> createMovement(
            @RequestBody KanbanCardMovementRequest request
    ) {

        var domain = mapper.toDomain(request);

        var result = useCase.create(domain);

        var response = mapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_VIEW')")
    @GetMapping("find/{movementId}")
    public ResponseEntity<KanbanCardMovementResponse> findById(
            @PathVariable("movementId") Long movementId
    ) {

        var result = useCase.findById(movementId);

        var response = mapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_VIEW')")
    @GetMapping("find-all")
    public ResponseEntity<List<KanbanCardMovementResponse>> findAll() {

        var result = useCase.findAll();

        var response = result.stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_VIEW')")
    @GetMapping("find-by-card/{cardId}")
    public ResponseEntity<List<KanbanCardMovementResponse>> findByCard(
            @PathVariable("cardId") Long cardId
    ) {
        var result = useCase.findByCard(cardId);
        var response = result.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_DELETE')")
    @DeleteMapping("delete/{movementId}")
    public ResponseEntity<Void> deleteMovement(
            @PathVariable("movementId") Long movementId
    ) {
        useCase.delete(movementId);
        return ResponseEntity.noContent().build();
    }
}