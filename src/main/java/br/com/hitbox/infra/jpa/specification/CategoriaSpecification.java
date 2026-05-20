package br.com.hitbox.infra.jpa.specification;

import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.entity.InventoryEntity;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.util.List;

public class CategoriaSpecification {

    private CategoriaSpecification() {}


    public static Specification<CategoriaEntity> byNome(String search){
    return (root, query, cb)-> {
        if (search == null || search.isEmpty()) {
            return cb.conjunction();
        }
        return cb.like(cb.lower(root.get("nome")), contains(search.toLowerCase()));
    };
    }
    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
}
