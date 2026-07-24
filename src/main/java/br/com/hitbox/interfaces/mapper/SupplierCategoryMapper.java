package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.SupplierCategory;
import br.com.hitbox.interfaces.dto.request.suppliers.SupplierCategoryRequest;
import br.com.hitbox.interfaces.dto.response.suppliers.SupplierCategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplierCategoryMapper {


    public SupplierCategory toDomain(SupplierCategoryRequest request) {
        return SupplierCategory.builder()
                .code(request.getCode())
                .name(request.getName())
                .active(request.getActive())
                .description(request.getDescription())
                .build();
    }

    public SupplierCategoryResponse toResponse(SupplierCategory domain) {
        return SupplierCategoryResponse.builder()
                .id(domain.getId())
                .description(domain.getDescription())
                .code(domain.getCode())
                .active(domain.getActive())
                .name(domain.getName())
                .build();
    }
}
