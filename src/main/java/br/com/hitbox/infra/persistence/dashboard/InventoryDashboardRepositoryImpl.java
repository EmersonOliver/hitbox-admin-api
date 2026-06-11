package br.com.hitbox.infra.persistence.dashboard;

import br.com.hitbox.infra.jpa.dashboard.InventoryDashboardRepository;
import br.com.hitbox.interfaces.dashboard.dto.InventoryMetricsDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryConsumptionDTO;
import br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryEntryDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryDashboardRepositoryImpl implements InventoryDashboardRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public InventoryMetricsDTO loadMetrics(
            Long inventoryId
    ) {

        Object[] result =
                (Object[]) em.createQuery("""
                    select
                        coalesce(sum(
                            case
                                when m.type = 'ENTRY'
                                then m.quantity
                                else 0
                            end
                        ),0),

                        coalesce(sum(
                            case
                                when m.type in (
                                    'OUTPUT',
                                    'PRODUCTION_CONSUMPTION'
                                )
                                then m.quantity
                                else 0
                            end
                        ),0),

                        i.quantity,

                        i.minimumStock,

                        coalesce(sum(
                            case
                                when m.type = 'ENTRY'
                                then m.totalCost
                                else 0
                            end
                        ),0),

                        coalesce(sum(
                            case
                                when m.type in (
                                    'OUTPUT',
                                    'PRODUCTION_CONSUMPTION'
                                )
                                then m.totalCost
                                else 0
                            end
                        ),0),

                        i.unitCost,

                        max(
                            case
                                when m.type = 'ENTRY'
                                then m.movementDate
                            end
                        ),

                        max(
                            case
                                when m.type in (
                                    'OUTPUT',
                                    'PRODUCTION_CONSUMPTION'
                                )
                                then m.movementDate
                            end
                        )

                    from InventoryEntity i
                    left join i.movements m
                    where i.id = :inventoryId
                    group by
                        i.quantity,
                        i.minimumStock,
                        i.unitCost
                    """)
                        .setParameter(
                                "inventoryId",
                                inventoryId
                        )
                        .getSingleResult();

        return InventoryMetricsDTO.builder()

                .purchasedQuantity(
                        (BigDecimal) result[0]
                )

                .consumedQuantity(
                        (BigDecimal) result[1]
                )

                .currentQuantity(
                        (BigDecimal) result[2]
                )

                .minimumStock(
                        (BigDecimal) result[3]
                )

                .totalPurchasedValue(
                        (BigDecimal) result[4]
                )

                .totalConsumedValue(
                        (BigDecimal) result[5]
                )

                .averageUnitCost(
                        (BigDecimal) result[6]
                )

                .lastEntryDate(
                        (LocalDateTime) result[7]
                )

                .lastConsumptionDate(
                        (LocalDateTime) result[8]
                )

                .build();
    }

    @Override
    public List<MonthlyInventoryEntryDTO>
    entriesHistory(
            Long inventoryId
    ) {

        return em.createQuery("""
            select new br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryEntryDTO(

                year(m.movementDate),

                month(m.movementDate),

                sum(m.quantity),

                sum(m.totalCost)

            )

            from StockMovementEntity m

            where
                m.inventory.id = :inventoryId
                and m.type = 'ENTRY'

            group by
                year(m.movementDate),
                month(m.movementDate)

            order by
                year(m.movementDate),
                month(m.movementDate)
            """,
                        MonthlyInventoryEntryDTO.class
                )
                .setParameter(
                        "inventoryId",
                        inventoryId
                )
                .getResultList();
    }

    @Override
    public List<MonthlyInventoryConsumptionDTO>
    consumptionHistory(
            Long inventoryId
    ) {

        return em.createQuery("""
            select new br.com.hitbox.interfaces.dashboard.dto.MonthlyInventoryConsumptionDTO(

                year(m.movementDate),

                month(m.movementDate),

                sum(m.quantity),

                sum(m.totalCost)

            )

            from StockMovementEntity m

            where
                m.inventory.id = :inventoryId
                and m.type in (
                    'OUTPUT',
                    'PRODUCTION_CONSUMPTION'
                )

            group by
                year(m.movementDate),
                month(m.movementDate)

            order by
                year(m.movementDate),
                month(m.movementDate)
            """,
                        MonthlyInventoryConsumptionDTO.class
                )
                .setParameter(
                        "inventoryId",
                        inventoryId
                )
                .getResultList();
    }
}
