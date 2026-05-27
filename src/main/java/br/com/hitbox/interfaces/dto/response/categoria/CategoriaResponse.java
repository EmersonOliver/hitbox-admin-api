package br.com.hitbox.interfaces.dto.response.categoria;

import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.enums.TipoCategoria;

public record CategoriaResponse(Long id,
                                String nome,
                                TipoCategoria tipo,
                                String descricao,
                                Boolean ativo) {

    public CategoriaResponse toResponse(CategoriaEntity entity) {
        return new CategoriaResponse(
                entity.getId(),
                entity.getNome(),
                entity.getTipo(),
                entity.getDescricao(),
                entity.getAtivo()
        );
    }
}
