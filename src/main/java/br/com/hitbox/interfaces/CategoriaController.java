package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.CategoriaUseCase;
import br.com.hitbox.infra.query.CategoriaQueryService;
import br.com.hitbox.interfaces.dto.CategoriaRecord;
import br.com.hitbox.interfaces.dto.CategoriaResponse;
import br.com.hitbox.interfaces.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaUseCase useCase;
    private final CategoriaQueryService queryService;

    @PostMapping("create")
    public ResponseEntity<Void> criarCategoria(@RequestBody CategoriaRecord req) {
        useCase.salvarCategoria(CategoriaMapper.toDomain(req));
        return ResponseEntity.ok().build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> atualizarCategoria(@RequestParam Long id, @RequestBody CategoriaRecord req) {
        useCase.atualizarCategoria(id
                , CategoriaMapper.toDomain(req));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("remove")
    public ResponseEntity<Void> removerCategoria(@RequestParam Long id) {
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("listAll")
    public ResponseEntity<Page<CategoriaResponse>> listarTodos(Pageable pageable,
                                                               @RequestParam(value = "search", required = false)
                                                               String search) {
        var response = queryService.categoriaLista(pageable, search);
        return ResponseEntity.ok(response);
    }
}
