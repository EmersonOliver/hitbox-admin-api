package br.com.hitbox.infra.jpa.specification;

import br.com.hitbox.infra.entity.InventoryEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class InventorySpecification {
    private InventorySpecification() {
    }

    public static Specification<InventoryEntity> byCategorias(List<Long> categoriasIds) {

        return (root, query, criteriaBuilder) -> {
            if (categoriasIds == null || categoriasIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root
                    .get("categoria")
                    .get("id")
                    .in(categoriasIds);
        };
    }
}
