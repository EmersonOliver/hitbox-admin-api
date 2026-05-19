package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.usecase.InventarioUseCase;
import br.com.hitbox.interfaces.dto.InventoryRequest;
import br.com.hitbox.interfaces.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Page<Inventory>> page(Pageable pageable) {
        return ResponseEntity.ok(useCase.page(pageable));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id){
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
