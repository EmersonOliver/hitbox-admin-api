package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Product;
import br.com.hitbox.infra.entity.ProductEntity;
import br.com.hitbox.infra.jpa.SpringDataProductRepository;
import br.com.hitbox.infra.jpa.specification.ProductSpecification;
import br.com.hitbox.infra.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final SpringDataProductRepository jpaRepository;
    private final ProductEntityMapper mapper;

    public Page<Product> listaAllByPage(Pageable pageable, List<Long> idCategorias, String search) {
        Specification<ProductEntity> specs = ProductSpecification.byCategorias(idCategorias, search);
        return jpaRepository.findAll(specs, pageable).map(mapper::toDomain);
    }

    public List<Product> findAllProducts() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    public Long countAll() {
        return jpaRepository.count();
    }
}
