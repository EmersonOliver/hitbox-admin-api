package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Suppliers;
import br.com.hitbox.infra.entity.SuppliersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierEntityMapper {

    private final SupplierCategoryEntityMapper categoryEntityMapper;
    private final SuppliersAddressEntityMapper addressEntityMapper;

    public Suppliers toDomain(SuppliersEntity entity) {
        return Suppliers.builder()
                .supplierId(entity.getId())
                .name(entity.getName())
                .contact(entity.getPhone())
                .email(entity.getEmail())
                .document(entity.getDocument())
                .category(categoryEntityMapper.toDomain(entity.getCategory()))
                .companyId(entity.getCompanyId())
                .active(entity.getActive())
                .build();
    }

    public SuppliersEntity toEntity(Suppliers domain) {
        return SuppliersEntity.builder()
                .id(domain.getSupplierId())
                .phone(domain.getContact())
                .document(domain.getDocument())
                .email(domain.getEmail())
                .category(categoryEntityMapper.toEntity(domain.getCategory()))
                .name(domain.getName())
                .companyId(domain.getCompanyId())
                .active(domain.getActive())
                .build();
    }

}
