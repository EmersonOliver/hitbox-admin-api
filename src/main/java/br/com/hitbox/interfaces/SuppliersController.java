package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.SuppliersUseCase;
import br.com.hitbox.infra.query.SupplierQueryService;
import br.com.hitbox.interfaces.dto.request.suppliers.SupplierRequest;
import br.com.hitbox.interfaces.dto.response.suppliers.SupplierResponse;
import br.com.hitbox.interfaces.mapper.SuppliersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("suppliers")
@RequiredArgsConstructor
public class SuppliersController {


    private final SuppliersUseCase useCase;
    private final SupplierQueryService queryService;
    private final SuppliersMapper mapper;

    @PostMapping("create")
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody SupplierRequest request) {
        useCase.execute(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("page/all")
    public ResponseEntity<Page<SupplierResponse>> listAllSuppliersPaginate(Pageable pageable) {
        var response = queryService.listAllSuppliers(pageable).map(mapper::toResponse);
        return ResponseEntity.ok(response);
    }
}
