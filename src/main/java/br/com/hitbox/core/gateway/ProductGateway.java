package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Product;

import java.util.Optional;


public interface ProductGateway {

    Product salvar(Product domain);

    Product editar(Long productId, Product domain);

    void remover(Long productId);

    Optional<Product> findById(Long productId);
}
