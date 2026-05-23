package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductGateway {

    Product salvar(Product domain);
    Product editar(Long productId, Product domain);
    void remover(Long productId);

}
