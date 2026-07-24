package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.SupplierCategory;

import java.util.Optional;

public interface SupplierCategoryGateway {

    SupplierCategory save(SupplierCategory domain);

    SupplierCategory update(Long id, SupplierCategory domain);

    void delete(Long id);

    Optional<SupplierCategory> findById(Long id);

}
