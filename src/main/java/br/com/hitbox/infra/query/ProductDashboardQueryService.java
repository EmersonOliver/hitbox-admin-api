package br.com.hitbox.infra.query;
import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.gateway.ProductGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.dashboard.ProductDashboardRepository;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyProductionDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyRevenueDTO;
import br.com.hitbox.interfaces.dashboard.dto.ProductMetricsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDashboardQueryService {
    private final ProductGateway productGateway;

    private final ProductDashboardRepository repository;

    public Product findProduct(Long productId) {

        return productGateway.findById(productId)
                .orElseThrow(
                        () -> new HitboxException(
                                "Produto não encontrado!"
                        )
                );
    }

    public ProductMetricsDTO loadMetrics(Long productId) {

        return repository.loadMetrics(productId);
    }

    public List<MonthlyProductionDTO> productionHistory(
            Long productId
    ) {

        return repository.productionHistory(
                productId
        );
    }

    public List<MonthlyRevenueDTO> revenueHistory(
            Long productId
    ) {

        return repository.revenueHistory(
                productId
        );
    }

    public Integer ranking(Long productId) {

        return repository.ranking(
                productId
        );
    }

    /**
     * Score de 1 a 5
     */
    public Integer popularityScore(Long productId) {

        Integer ranking =
                repository.ranking(productId);

        if (ranking == null || ranking <= 0) {
            return 0;
        }

        long totalProducts =
                repository.countRankedProducts();

        if (totalProducts == 0) {
            return 0;
        }

        double percentile =
                1.0 -
                        ((double) (ranking - 1)
                                / totalProducts);

        return Math.max(
                1,
                (int) Math.ceil(percentile * 5)
        );
    }
}
