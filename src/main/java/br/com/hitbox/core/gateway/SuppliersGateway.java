package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Suppliers;

import java.util.List;
import java.util.Optional;

public interface SuppliersGateway {

    Suppliers save(Suppliers domain);

    Suppliers update(Long idSupplier, Suppliers domain);

    Optional<Suppliers> findById(Long idSupplier);

    List<Suppliers> findByDocumentAndName(String document, String name);

    void delete(Long idSupplier);
}
