package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.entity.PricingRuleEntity;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataCategoriaRepository;
import br.com.hitbox.infra.jpa.SpringDataPricingRuleRepository;
import br.com.hitbox.infra.mapper.PricingRulePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PricingRuleRepositoryImpl {

    private final SpringDataPricingRuleRepository repository;

    private final SpringDataCategoriaRepository categoriaRepository;

    public PricingRule salvar(
            PricingRule domain
    ) {

        CategoriaEntity categoriaEntity =
                categoriaRepository.findById(
                        domain.getCategoriaId()
                ).orElseThrow(() ->
                        new HitboxException(
                                "Categoria não encontrada!"
                        )
                );

        PricingRuleEntity entity =
                PricingRulePersistenceMapper
                        .toEntity(domain);

        entity.setCategoria(
                categoriaEntity
        );

        entity =
                repository.save(entity);

        return PricingRulePersistenceMapper
                .toDomain(entity);
    }

    public Page<PricingRule> page(
            Pageable pageable
    ) {

        return repository
                .findAll(pageable)
                .map(
                        PricingRulePersistenceMapper::toDomain
                );
    }
}