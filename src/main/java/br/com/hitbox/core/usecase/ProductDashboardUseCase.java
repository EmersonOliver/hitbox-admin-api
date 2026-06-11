package br.com.hitbox.core.usecase;

import br.com.hitbox.core.aggregator.ProductDashboardAggregate;
import br.com.hitbox.infra.query.ProductDashboardQueryService;
import br.com.hitbox.infra.query.ServiceOrderQueryService;
import br.com.hitbox.interfaces.dashboard.dto.ProductDashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDashboardUseCase {

    private final ProductDashboardQueryService queryService;

    private final ProductDashboardAggregate aggregate;



    public ProductDashboardResponse execute(
            Long productId
    ) {

        var product =
                queryService.findProduct(productId);

        var metrics =
                queryService.loadMetrics(productId);

        var productionHistory =
                queryService.productionHistory(productId);

        var revenueHistory =
                queryService.revenueHistory(productId);

        var ranking =
                queryService.ranking(productId);

        var popularityScore =
                queryService.popularityScore(productId);

        return aggregate.aggregate(
                product,
                metrics,
                ranking,
                popularityScore,
                queryService.delivered(productId),
                productionHistory,
                revenueHistory
        );
    }
}
