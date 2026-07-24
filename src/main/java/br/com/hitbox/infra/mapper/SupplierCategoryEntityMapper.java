package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.SupplierCategory;
import br.com.hitbox.infra.entity.SupplierCategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierCategoryEntityMapper {

    public SupplierCategory toDomain(SupplierCategoryEntity entity) {
        return SupplierCategory.builder()
                .id(entity.getId())
                .active(entity.getActive())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public SupplierCategoryEntity toEntity(SupplierCategory domain) {
        return SupplierCategoryEntity.builder()
                .id(domain.getId())
                .active(domain.getActive())
                .code(domain.getCode())
                .description(domain.getDescription())
                .name(domain.getName())
                .build();
    }

}
