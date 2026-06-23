package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Product;
import br.com.hitbox.infra.query.InventarioQueryService;
import br.com.hitbox.infra.query.ServiceOrderQueryService;
import br.com.hitbox.interfaces.dashboard.dto.DashboardResponse;
import br.com.hitbox.interfaces.dashboard.dto.ProductScore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DashboardUseCase {


    private final ServiceOrderQueryService serviceOrderQueryService;
    private final InventarioQueryService inventarioQueryService;


    public DashboardResponse dashboardResponse() {
        var ranking =
                serviceOrderQueryService.findTopProducts(
                        PageRequest.of(0, 5)
                );
        var topInventorys =
                inventarioQueryService.listTopInventory(PageRequest.of(0, 5,
                                Sort.by("quantity").ascending()))
                        ;
        if (ranking.isEmpty() && !topInventorys.isEmpty()) {
            return DashboardResponse.builder()
                    .topProducts(null)
                    .topInventorys(topInventorys.getContent())
                    .build();
        }
        if (ranking.isEmpty() && topInventorys.isEmpty()) {
            return DashboardResponse.builder()
                    .build();
        }
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

        return DashboardResponse.builder()
                .topProducts(scores)
                .topInventorys(topInventorys.getContent())
                .build();
    }


}
