package br.com.hitbox.interfaces.dto;

import br.com.hitbox.infra.enums.TipoCategoria;

public record CategoriaRecord(
        Long id,
        String nome,
        TipoCategoria tipo,
        String descricao,
        Boolean ativo
) {
}
