package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.core.gateway.CategoriaGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.persistence.PricingRuleRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricingRuleUseCase {

    private final PricingRuleRepositoryImpl repository;
    private final CategoriaGateway categoriaGateway;

    public PricingRule salvar(
            PricingRule domain
    ) {

        validar(domain);

        Categoria categoria =
                categoriaGateway
                        .buscarPorId(
                                domain.getCategoriaId()
                        );

        domain.setCategoria(categoria);

        return repository.salvar(domain);
    }

    public Page<PricingRule> page(
            Pageable pageable
    ) {
        return repository.page(pageable);
    }

    private void validar(
            PricingRule domain
    ) {

        if (
                domain.getName() == null
                        || domain.getName().isBlank()
        ) {
            throw new HitboxException(
                    "Nome da regra é obrigatório"
            );
        }

        if (
                domain.getCalculationType() == null
        ) {
            throw new HitboxException(
                    "Tipo de cálculo obrigatório"
            );
        }

        if (
                domain.getProfitMargin() != null
                        &&
                        domain.getProfitMargin().doubleValue() < 0
        ) {
            throw new HitboxException(
                    "Margem de lucro inválida"
            );
        }

        if (
                domain.getMinimumPrice() != null
                        &&
                        domain.getMinimumPrice().doubleValue() < 0
        ) {
            throw new HitboxException(
                    "Preço mínimo inválido"
            );
        }
    }
}
