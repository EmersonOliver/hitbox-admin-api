package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.usecase.InventarioUseCase;
import br.com.hitbox.infra.enums.TipoCategoria;
import br.com.hitbox.interfaces.dto.request.inventario.InventoryRequest;
import br.com.hitbox.interfaces.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventarioUseCase useCase;

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
        return ResponseEntity.ok(useCase.page(pageable,idCategorias, search));
    }

    @GetMapping("/loadByCategory")
    public ResponseEntity<List<Inventory>> listAllInventoryByCategory(@RequestParam TipoCategoria tipoCategoria){
        return ResponseEntity.ok(useCase.listAllByCategoria(tipoCategoria));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id){
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
