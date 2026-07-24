package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.SupplierCategory;
import br.com.hitbox.core.usecase.SupplierCategoryUseCase;
import br.com.hitbox.infra.query.SupplierQueryService;
import br.com.hitbox.interfaces.dto.request.suppliers.SupplierCategoryRequest;
import br.com.hitbox.interfaces.dto.response.suppliers.SupplierCategoryResponse;
import br.com.hitbox.interfaces.mapper.SupplierCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("suppliers/category")
public class SuppliersCategoryController {

    private final SupplierCategoryUseCase useCase;
    private final SupplierQueryService supplierQueryService;
    private final SupplierCategoryMapper mapper;

    @PostMapping("create")
    public ResponseEntity<SupplierCategory> createSupplierCategory(@RequestBody SupplierCategoryRequest request) {
        useCase.execute(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<SupplierCategoryResponse>> listAllParameters() {
        var result = supplierQueryService.listAllSuppliersCategoryWithoutPages();
        var response = result.stream().map(mapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

}
