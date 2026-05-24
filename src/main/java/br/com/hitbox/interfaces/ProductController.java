package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.ProductUseCase;
import br.com.hitbox.interfaces.dto.produto.ProductRequest;
import br.com.hitbox.interfaces.dto.produto.ProductResponse;
import br.com.hitbox.interfaces.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductMapper mapper;


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

    @GetMapping("page")
    public ResponseEntity<Page<ProductResponse>> page(Pageable pageable,
                                                      @RequestParam(required = false) List<Long> idCategorias,
                                                      @RequestParam(value = "search", required = false) String search) {
        var result = productUseCase.listAllPage(pageable,idCategorias, search).map(mapper::toResponse);
        return ResponseEntity.ok(result);
    }
}
