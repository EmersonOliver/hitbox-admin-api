package br.com.hitbox.infra.jpa.specification;


import br.com.hitbox.infra.entity.ClienteEntity;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {


    public Specification<ClienteEntity> specs(String search) {
        return (root, query, builder) -> {

            if (search == null || search.isBlank()) {
                return builder.conjunction();
            }

            String term =
                    "%" + search.toLowerCase().trim() + "%";

            return builder.or(

                    builder.like(
                            builder.lower(root.get("nome")),
                            term
                    ),

                    builder.like(
                            builder.lower(root.get("email")),
                            term
                    ),

                    builder.like(
                            builder.lower(root.get("telefone")),
                            term
                    )
            );
        };
    }
}
