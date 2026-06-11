package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.ServiceOrderEntity;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import br.com.hitbox.infra.jpa.projections.ProductRankingProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataServiceOrderRepository extends JpaRepository<ServiceOrderEntity, Long> {

    List<ServiceOrderEntity> findByStatus(
            ServiceOrderStatus status
    );

    List<ServiceOrderEntity> findByClienteId(
            UUID clienteId
    );

    List<ServiceOrderEntity> findByExpectedDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );


    @Query("""
    select
        i.product.id as productId,
        i.product.name as productName,
        i.product.imageUrl as imageUrl,
        i.product.pricingRule.minimumPrice as price,
        sum(i.quantity) as totalOrders
    from ItemProductEntity i
    where i.serviceOrder.status <> 'CANCELED'
    group by
        i.product.id,
        i.product.name,
         i.product.pricingRule.minimumPrice,
         i.product.imageUrl
    order by sum(i.quantity) desc
""")
    List<ProductRankingProjection> findTopProducts(Pageable pageable);

}
