package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Suppliers;
import br.com.hitbox.core.gateway.SuppliersGateway;
import br.com.hitbox.infra.jpa.SpringDataSupplierRepository;
import br.com.hitbox.infra.mapper.SupplierEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SupplierRepositoryImpl implements SuppliersGateway {

    private final SupplierEntityMapper supplierEntityMapper;
    private final SpringDataSupplierRepository repository;

    @Override
    public Suppliers save(Suppliers domain) {
        var entity = supplierEntityMapper.toEntity(domain);
        return supplierEntityMapper.toDomain(repository.save(entity));
    }

    @Override
    public Suppliers update(Long idSupplier, Suppliers domain) {
        return null;
    }

    @Override
    public Optional<Suppliers> findById(Long idSupplier) {
        return Optional.empty();
    }

    @Override
    public List<Suppliers> findByDocumentAndName(String document, String name) {
        return List.of();
    }

    @Override
    public void delete(Long idSupplier) {

    }
}
