package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.core.domain.ProductPricingContext;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.persistence.PricingRuleRepositoryImpl;
import br.com.hitbox.infra.service.PricingEngineService;
import br.com.hitbox.interfaces.dto.response.pricing.SuggestedPriceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingRuleUseCase {

    private final PricingRuleRepositoryImpl repository;
    private final PricingEngineService engineService;

    public PricingRule salvar(
            PricingRule domain
    ) {
        validar(domain);
        return repository.salvar(domain);
    }

    public PricingRule editar(
            PricingRule domain
    ) {
        validar(domain);
        return repository.editar(domain);
    }

    public Page<PricingRule> page(
            Pageable pageable
    ) {
        return repository.page(pageable);
    }

    private void validar(
            PricingRule domain
    ) {
        if (domain.getName() == null
                || domain.getName().isBlank()) {
            throw new HitboxException(
                    "Nome da regra é obrigatório"
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

    public List<SuggestedPriceResult> suggestedPriceRule(
            ProductPricingContext context
    ) {

        List<SuggestedPriceResult> suggestedPrices =
                new ArrayList<>();

        var rules = repository.findAll();

        for (var rule : rules) {

            var suggested =
                    engineService.calculate(
                            context,
                            rule
                    );

            suggestedPrices.add(suggested);
        }

        return suggestedPrices;
    }

    public void deleteRule(Long ruleId) {
        this.repository.deleteRuleBy(ruleId);
    }
}
