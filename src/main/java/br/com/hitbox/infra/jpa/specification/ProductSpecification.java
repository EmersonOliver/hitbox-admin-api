package br.com.hitbox.infra.jpa.specification;

import br.com.hitbox.infra.entity.InventoryEntity;
import br.com.hitbox.infra.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    private ProductSpecification() {
    }

    public static Specification<ProductEntity> byCategorias(
            List<Long> categoriasIds, String search) {

        List<Specification<ProductEntity>> list = new ArrayList<>();
        if (categoriasIds != null && !categoriasIds.isEmpty()) {
            list.add(byCategoriasId(categoriasIds));
        }
        if (search != null && !search.isEmpty()) {
            list.add(bySearch(search));
        }

        Specification<ProductEntity> specs = null;
        if (!list.isEmpty()) {
            specs = Specification.where(list.getFirst());
        }
        for (int i = 1; i < list.size(); i++) {
            specs = specs.and(list.get(i));
        }
        return specs;
    }

    private static Specification<ProductEntity> byCategoriasId(List<Long> categoriasIds) {
        return (root, query, criteriaBuilder) -> {
            return root
                    .get("categoria")
                    .get("id")
                    .in(categoriasIds);
        };
    }

    private static Specification<ProductEntity> bySearch(String search) {
        return (root, query, cBuilder)
                -> cBuilder.like(cBuilder.lower(root.get("name")), contains(search.toLowerCase()));
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
}
