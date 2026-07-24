package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.SupplierCategory;
import br.com.hitbox.core.domain.Suppliers;
import br.com.hitbox.infra.jpa.SpringDataSupplierCategoryRepository;
import br.com.hitbox.infra.jpa.SpringDataSupplierRepository;
import br.com.hitbox.infra.mapper.SupplierCategoryEntityMapper;
import br.com.hitbox.infra.mapper.SupplierEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierQueryService {

    private final SpringDataSupplierCategoryRepository supplierCategoryRepository;
    private final SpringDataSupplierRepository supplierRepository;
    private final SupplierEntityMapper supplierEntityMapper;
    private final SupplierCategoryEntityMapper supplierCategoryEntityMapper;

    public Page<Suppliers> listAllSuppliers(Pageable pageable) {
        return supplierRepository.findAll(pageable).map(supplierEntityMapper::toDomain);
    }

    public Page<SupplierCategory> listAllSuppliersCategory(Pageable pageable) {
        return supplierCategoryRepository.findAll(pageable).map(supplierCategoryEntityMapper::toDomain);
    }

    public List<SupplierCategory> listAllSuppliersCategoryWithoutPages() {
        return supplierCategoryRepository.findAll().stream().map(supplierCategoryEntityMapper::toDomain).toList();
    }

}
