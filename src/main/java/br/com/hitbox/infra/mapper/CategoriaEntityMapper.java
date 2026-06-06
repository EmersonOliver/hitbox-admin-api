package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.infra.entity.CategoriaEntity;

public class CategoriaEntityMapper {

    public static CategoriaEntity toEntity(Categoria domain) {
        return CategoriaEntity.builder()
                .tipo(domain.getTipo())
                .nome(domain.getNome())
                .descricao(domain.getDescricao())
                .ativo(domain.getAtivo())
                .companyId(domain.getCompanyId())
                .build();
    }

    public static Categoria toDomain(CategoriaEntity entity) {
        return Categoria.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .tipo(entity.getTipo())
                .descricao(entity.getDescricao())
                .ativo(entity.getAtivo())
                .companyId(entity.getCompanyId())
                .build();
    }
}
