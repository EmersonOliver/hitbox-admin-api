package br.com.hitbox.interfaces.kanban;


import br.com.hitbox.core.usecase.kanban.KanbanColumnUseCase;
import br.com.hitbox.interfaces.dto.request.kanban.KanbanColumnRequest;
import br.com.hitbox.interfaces.dto.response.kanban.KanbanColumnResponse;
import br.com.hitbox.interfaces.mapper.kanban.KanbanColumnMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kanban/column")
@RequiredArgsConstructor
public class KanbanColumnController {

    private final KanbanColumnUseCase useCase;

    private final KanbanColumnMapper mapper;

    @PreAuthorize("hasAuthority('PRODUCTION_CREATE')")
    @PostMapping("create")
    public ResponseEntity<KanbanColumnResponse> createColumn(
            @RequestBody KanbanColumnRequest request) {
        var domain = mapper.toDomain(request);
        var result = useCase.create(domain);
        var response = mapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_UPDATE')")
    @PutMapping("edit/{columnId}")
    public ResponseEntity<KanbanColumnResponse> editColumn(
            @PathVariable("columnId") Long columnId,
            @RequestBody KanbanColumnRequest request) {
        var domain = mapper.toDomain(request);
        domain.setId(columnId);

        var result = useCase.update(domain);
        var response = mapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_VIEW')")
    @GetMapping("find/{columnId}")
    public ResponseEntity<KanbanColumnResponse> findById(
            @PathVariable("columnId") Long columnId
    ) {

        var result = useCase.findById(columnId);

        var response = mapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_VIEW')")
    @GetMapping("find-all")
    public ResponseEntity<List<KanbanColumnResponse>> findAll() {

        var result = useCase.findAll();

        var response = result.stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCTION_DELETE')")
    @DeleteMapping("delete/{columnId}")
    public ResponseEntity<Void> deleteColumn(
            @PathVariable("columnId") Long columnId
    ) {

        useCase.delete(columnId);

        return ResponseEntity.noContent().build();
    }
}
