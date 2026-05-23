package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.domain.StockMovement;
import br.com.hitbox.core.gateway.InventarioGateway;
import br.com.hitbox.core.gateway.StockMovementGateway;
import br.com.hitbox.infra.enums.StockMovementType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StockMovementUseCase {

    private final InventarioGateway inventarioGateway;
    private final StockMovementGateway movementGateway;

    public void movimentar(
            Long inventoryId,
            StockMovementType type,
            BigDecimal quantity,
            BigDecimal totalCost,
            String observation
    ) {

        Inventory inventory =
                inventarioGateway.findById(inventoryId);

        BigDecimal unitCost =
                calcularCustoUnitario(
                        quantity,
                        totalCost,
                        inventory
                );

        StockMovement movement =
                StockMovement.builder()
                        .inventory(inventory)
                        .type(type)
                        .quantity(quantity)
                        .unitCost(unitCost)
                        .totalCost(
                                calcularTotalCost(
                                        type,
                                        quantity,
                                        totalCost,
                                        inventory
                                )
                        )
                        .observation(observation)
                        .movementDate(LocalDateTime.now())
                        .build();

        inventory.addMovement(movement);

        inventarioGateway.atualizar(inventory);

        movementGateway.salvar(movement);
    }

    private BigDecimal calcularCustoUnitario(
            BigDecimal quantity,
            BigDecimal totalCost,
            Inventory inventory
    ) {

        if (
                totalCost == null ||
                        quantity == null ||
                        quantity.compareTo(BigDecimal.ZERO) <= 0
        ) {

            return inventory.getUnitCost();
        }

        return totalCost.divide(
                quantity,
                4,
                RoundingMode.HALF_EVEN
        );
    }

    private BigDecimal calcularTotalCost(
            StockMovementType type,
            BigDecimal quantity,
            BigDecimal totalCost,
            Inventory inventory
    ) {

        if (
                type == StockMovementType.ENTRY ||
                        type == StockMovementType.ADJUSTMENT
        ) {

            return totalCost;
        }

        return inventory.getUnitCost()
                .multiply(quantity);
    }
}
