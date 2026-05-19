package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.interfaces.dto.CategoriaRecord;
import br.com.hitbox.interfaces.dto.CategoriaResponse;

public class CategoriaMapper {


    public static CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getTipo(),
                categoria.getDescricao(),
                categoria.getAtivo()
        );
    }

    public static CategoriaResponse entityToResponse(CategoriaEntity categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getTipo(),
                categoria.getDescricao(),
                categoria.getAtivo()
        );
    }

    public static Categoria toDomain(CategoriaRecord req) {
        return Categoria.builder()
                .id(req.id())
                .nome(req.nome())
                .tipo(req.tipo())
                .descricao(req.descricao())
                .ativo(req.ativo())
                .build();
    }
}
