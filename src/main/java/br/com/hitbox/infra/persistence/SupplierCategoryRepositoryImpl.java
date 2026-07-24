package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.SupplierCategory;
import br.com.hitbox.core.gateway.SupplierCategoryGateway;
import br.com.hitbox.infra.jpa.SpringDataSupplierCategoryRepository;
import br.com.hitbox.infra.mapper.SupplierCategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SupplierCategoryRepositoryImpl implements SupplierCategoryGateway {

    private final SupplierCategoryEntityMapper mapper;
    private final SpringDataSupplierCategoryRepository repository;


    @Override
    public SupplierCategory save(SupplierCategory domain) {
        var category = mapper.toEntity(domain);
        return mapper.toDomain(repository.save(category));
    }

    @Override
    public SupplierCategory update(Long id, SupplierCategory domain) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<SupplierCategory> findById(Long id) {
        return Optional.empty();
    }
}
