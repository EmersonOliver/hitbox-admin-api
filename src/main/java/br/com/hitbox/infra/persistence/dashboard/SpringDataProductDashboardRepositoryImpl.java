package br.com.hitbox.infra.persistence.dashboard;

import br.com.hitbox.infra.jpa.dashboard.ProductDashboardRepository;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyProductionDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyRevenueDTO;
import br.com.hitbox.interfaces.dashboard.dto.ProductMetricsDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpringDataProductDashboardRepositoryImpl   implements ProductDashboardRepository {

    @PersistenceContext
    private EntityManager em;


    public ProductMetricsDTO loadMetrics(Long productId) {

        Object[] result =
                (Object[]) em.createQuery("""
                                select
                                    coalesce(sum(i.quantity),0),
                                    coalesce(sum(i.quantity),0),
                                    count(distinct so.id),
                                    coalesce(sum(i.totalSalePrice),0),
                                    coalesce(sum(i.totalItemCost),0),
                                    coalesce(sum(i.estimatedMinutes),0)
                                from ItemProductEntity i
                                join i.serviceOrder so
                                where i.product.id = :productId
                                """)
                        .setParameter("productId", productId)
                        .getSingleResult();

        return ProductMetricsDTO.builder()
                .producedQuantity(
                        ((Number) result[0]).longValue()
                )
                .soldQuantity(
                        ((Number) result[1]).longValue()
                )
                .serviceOrdersCount(
                        ((Number) result[2]).longValue()
                )
                .totalRevenue(
                        (BigDecimal) result[3]
                )
                .totalProductionCost(
                        (BigDecimal) result[4]
                )
                .totalMinutes(
                        (BigDecimal) result[5]
                )
                .build();
    }

    public List<MonthlyProductionDTO> productionHistory(
            Long productId
    ) {

        return em.createQuery("""
                                select new br.com.hitbox.interfaces.dashboard.dto.MonthlyProductionDTO(
                                    year(so.createdAt),
                                    month(so.createdAt),
                                    cast(sum(i.quantity) as long)
                                )
                                from ItemProductEntity i
                                join i.serviceOrder so
                                where i.product.id = :productId
                                group by
                                    year(so.createdAt),
                                    month(so.createdAt)
                                order by
                                    year(so.createdAt),
                                    month(so.createdAt)
                                """,
                        MonthlyProductionDTO.class
                )
                .setParameter("productId", productId)
                .getResultList();
    }


    public List<MonthlyRevenueDTO> revenueHistory(
            Long productId
    ) {

        return em.createQuery("""
                                select new br.com.hitbox.interfaces.dashboard.dto.MonthlyRevenueDTO(
                                    year(so.createdAt),
                                    month(so.createdAt),
                                    sum(i.totalSalePrice),
                                    sum(i.totalItemCost),
                                    sum(i.totalSalePrice) - sum(i.totalItemCost)
                                )
                                from ItemProductEntity i
                                join i.serviceOrder so
                                where i.product.id = :productId
                                group by
                                    year(so.createdAt),
                                    month(so.createdAt)
                                order by
                                    year(so.createdAt),
                                    month(so.createdAt)
                                """,
                        MonthlyRevenueDTO.class
                )
                .setParameter("productId", productId)
                .getResultList();
    }


    public Integer ranking(Long productId) {

        List<Long> ranking =
                em.createQuery("""
                                select i.product.id
                                from ItemProductEntity i
                                group by i.product.id
                                order by count(i.id) desc
                                """,
                        Long.class
                ).getResultList();

        int index =
                ranking.indexOf(productId);

        return index >= 0
                ? index + 1
                : 0;
    }



    @Override
    public Long countRankedProducts() {

        return em.createQuery("""
        select count(distinct i.product.id)
        from ItemProductEntity i
        """, Long.class)
                .getSingleResult();
    }

    @Override
    public BigDecimal countDelivered(Long productId) {
      return
                em.createQuery("""
                                select sum(i.quantity)
                                from ItemProductEntity i
                                
                                where i.serviceOrder.status  = 'DELIVERED' and i.product.id = :productId
                                
                                """,
                        BigDecimal.class
                )  .setParameter("productId", productId)
                        .getSingleResult();

    }
}
