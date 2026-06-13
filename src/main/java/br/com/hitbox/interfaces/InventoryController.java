package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.usecase.InventarioUseCase;
import br.com.hitbox.infra.enums.TipoCategoria;
import br.com.hitbox.infra.query.InventarioQueryService;
import br.com.hitbox.interfaces.dto.request.inventario.InventoryRequest;
import br.com.hitbox.interfaces.dto.request.inventario.StockMovementValidateRequest;
import br.com.hitbox.interfaces.dto.response.inventario.InventarioInsuficienteResponse;
import br.com.hitbox.interfaces.dto.response.inventario.StockMovementResponse;
import br.com.hitbox.interfaces.mapper.InventoryMapper;
import br.com.hitbox.interfaces.mapper.StockMovementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventarioUseCase useCase;
    private final InventarioQueryService inventarioQueryService;
    private final StockMovementMapper mapper;

    @PostMapping(
            value = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> save(
            @RequestPart("data") InventoryRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        Inventory domain = InventoryMapper.toDomain(request);
        var response = useCase.salvar(domain, image);
        return ResponseEntity.ok(response);
    }

    @PutMapping(
            value = "/edit/{idInventario}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> edit(
            @PathVariable("idInventario") Long inventarioId,
            @RequestPart("data") InventoryRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        Inventory domain = InventoryMapper.toDomain(request);
        domain.setId(inventarioId);

        var response = useCase.editar(domain, image);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Inventory>> page(Pageable pageable,
                                                @RequestParam(required = false) List<Long> idCategorias,
                                                @RequestParam(value = "search", required = false) String search) {
        return ResponseEntity.ok(useCase.page(pageable, idCategorias, search));
    }

    @GetMapping("/loadByCategory")
    public ResponseEntity<List<Inventory>> listAllInventoryByCategory(@RequestParam TipoCategoria tipoCategoria) {
        return ResponseEntity.ok(useCase.listAllByCategoria(tipoCategoria));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/available")
    public ResponseEntity<List<InventarioInsuficienteResponse>> validInventoryAvailable(
            @RequestBody List<StockMovementValidateRequest> request
    ) {
        List<InventarioInsuficienteResponse> response = new ArrayList<>();
        request.forEach(req -> {
            var result = useCase.validateInventoryAvailable(req.getInventoryId(), req.getQuantity());
            response.add(result);
        });
        var hasInvalid = response.stream()
                .anyMatch(rs -> !Objects.isNull(rs.getMessage()));
        if (hasInvalid) {
            return ResponseEntity.badRequest().body(response.stream().filter(
                    rs -> Boolean.FALSE.equals(rs.getValid())).toList());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{inventoryId}/movements")
    public Page<StockMovementResponse> findMovements(
            @PathVariable Long inventoryId,
            Pageable pageable
    ) {
        return inventarioQueryService.findMovements(
                inventoryId,
                pageable
        ).map(mapper::toResponse);
    }
}
