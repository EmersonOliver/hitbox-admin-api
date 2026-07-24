package br.com.hitbox.core.usecase;

import br.com.hitbox.core.gateway.SupplierCategoryGateway;
import br.com.hitbox.interfaces.dto.request.suppliers.SupplierCategoryRequest;
import br.com.hitbox.interfaces.mapper.SupplierCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierCategoryUseCase {

    private final SupplierCategoryGateway gateway;
    private final SupplierCategoryMapper mapper;


    public void execute(SupplierCategoryRequest request) {
        var domain = mapper.toDomain(request);
        gateway.save(domain);
    }


}
