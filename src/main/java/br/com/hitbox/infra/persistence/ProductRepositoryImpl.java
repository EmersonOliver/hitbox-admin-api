package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.gateway.ProductGateway;
import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.entity.ProductEntity;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataCategoriaRepository;
import br.com.hitbox.infra.jpa.SpringDataProductRepository;
import br.com.hitbox.infra.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductGateway {

    private final SpringDataProductRepository jpaRepository;
    private final SpringDataCategoriaRepository categoriaRepository;
    private final ProductEntityMapper mapper;

    @Override
    public Product salvar(Product domain) {
        var entity = mapper.toEntity(domain);
        validateCategory(entity, domain.getCategoriaId());
        entity = jpaRepository.save(entity);
        return mapper.toDomain(entity);
    }

    private void validateCategory(ProductEntity entity, Long categoriaId) {
        CategoriaEntity categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new HitboxException("Categoria não encontrada!"));
        entity.setCategoria(categoria);
    }

    @Override
    public Product editar(Long productId, Product domain) {
        ProductEntity entity = jpaRepository.findById(productId)
                .orElseThrow(() -> new HitboxException("Produto não encontrado!"));
        mapper.toEntityUpdate(domain, entity);
        jpaRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public void remover(Long productId) {
        var entityToDelete = jpaRepository.findById(productId).orElseThrow(() -> new HitboxException("Produto não encontrado para exclusão!"));
        jpaRepository.delete(entityToDelete);
    }

    @Override
    public Optional<Product> findById(Long productId) {

        return jpaRepository.findById(productId).map(mapper::toDomain);
    }


}
