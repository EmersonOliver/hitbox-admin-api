package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.ProductUseCase;
import br.com.hitbox.interfaces.dto.request.produto.ProductRequest;
import br.com.hitbox.interfaces.dto.response.produto.ProductResponse;
import br.com.hitbox.interfaces.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductMapper mapper;

    @PreAuthorize("hasAuthority('PRODUCT_CREATE')")
    @PostMapping(value = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(
            @RequestPart("data") ProductRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        var domain =
                mapper.toDomain(request);

        var saved =
                productUseCase.execute(domain, image);

        var response =
                mapper.toResponse(saved);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    @PutMapping(value = "/edit/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> edit(
            @RequestPart("data") ProductRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @PathVariable("id") Long productId
    ) {
        var domain =
                mapper.toDomain(request);
        var edited =
                productUseCase.editar(domain, image, productId);
        var response =
                mapper.toResponse(edited);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('PRODUCT_VIEW')")
    @GetMapping("page")
    public ResponseEntity<Page<ProductResponse>> page(Pageable pageable,
                                                      @RequestParam(required = false) List<Long> idCategorias,
                                                      @RequestParam(value = "search", required = false) String search) {
        var result = productUseCase.listAllPage(pageable, idCategorias, search).map(mapper::toResponse);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('PRODUCT_DELETE')")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
        productUseCase.delete(productId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('PRODUCT_VIEW', 'SERVICE_ORDER_VIEW')")
    @GetMapping("findAll")
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        var response = productUseCase.findAll().stream().map(mapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}
