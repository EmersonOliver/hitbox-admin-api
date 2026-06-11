package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.gateway.ProductGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.dashboard.ProductDashboardRepository;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyProductionDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyRevenueDTO;
import br.com.hitbox.interfaces.dashboard.dto.ProductMetricsDTO;
import br.com.hitbox.interfaces.dashboard.dto.ProductScore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDashboardQueryService {
    private final ProductGateway productGateway;

    private final ProductDashboardRepository repository;

    private final ServiceOrderQueryService serviceOrderQueryService;

    public Product findProduct(Long productId) {

        return productGateway.findById(productId)
                .orElseThrow(
                        () -> new HitboxException(
                                "Produto não encontrado!"
                        )
                );
    }

    public BigDecimal delivered(Long productId) {
        return repository.countDelivered(productId);
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
        var ranking =
                serviceOrderQueryService.findTopProducts(
                        PageRequest.of(0, 5)
                );
        long maxOrders =
                ranking.getFirst()
                        .getTotalOrders();

        List<ProductScore> scores =
                ranking.stream()
                        .map(item -> {

                            int score =
                                    maxOrders == 0
                                            ? 0
                                            : (int) Math.ceil(
                                            ((double) item.getTotalOrders()
                                             / maxOrders) * 5
                                    );

                            Product product =
                                    Product.builder()
                                            .productId(item.getProductId())
                                            .name(item.getProductName())
                                            .currentSalePrice(item.getPrice())
                                            .imageUrl(item.getImageUrl())
                                            .build();

                            return ProductScore.builder()
                                    .product(product)
                                    .score(score)
                                    .build();
                        })
                        .toList();

        return scores.stream().filter(rs ->
                rs.getProduct().getProductId().equals(productId))
                .findFirst()
                .map(ProductScore::getScore)
                .orElse(0);
    }
}
