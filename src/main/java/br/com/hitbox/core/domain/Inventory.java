package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.InventoryUnit;
import br.com.hitbox.infra.enums.StockMovementType;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Inventory {
    private Long id;

    private String name;

    private Long categoriaId;

    private Categoria categoria;
    private UUID companyId;
    @Builder.Default
    private BigDecimal quantity =
            BigDecimal.ZERO;

    private InventoryUnit unit;

    @Builder.Default
    private BigDecimal minimumStock =
            BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal cost =
            BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal unitCost =
            BigDecimal.ZERO;

    private String supplier;

    private String location;

    private String imageUrl;

    private Boolean active;

    @Builder.Default
    private List<StockMovement> movements =
            new ArrayList<>();

    public void addMovement(
            StockMovement movement
    ) {

        validarMovimento(movement);

        switch (movement.getType()) {

            case ENTRY -> processarEntrada(movement);

            case OUTPUT,
                 LOSS,
                 PRODUCTION_CONSUMPTION -> processarSaida(movement);

            case ADJUSTMENT -> processarAjuste(movement);
        }

        this.unitCost =
                custoUnitario();


        movements.add(movement);
    }

    private void processarEntrada(
            StockMovement movement
    ) {

        this.quantity =
                this.quantity.add(
                        movement.getQuantity()
                );

        this.cost =
                this.cost.add(
                        movement.getTotalCost()
                );
    }

    private void processarSaida(
            StockMovement movement
    ) {

        if (
                this.quantity.compareTo(
                        movement.getQuantity()
                ) < 0
        ) {

            throw new HitboxException(
                    "Estoque insuficiente"
            );
        }

        this.quantity =
                this.quantity.subtract(
                        movement.getQuantity()
                );

        BigDecimal custoUnitarioAtual =
                this.unitCost;
        BigDecimal custoSaida =
                custoUnitarioAtual
                        .multiply(
                                movement.getQuantity()
                        );

        this.cost =
                this.cost.subtract(
                        custoSaida
                );
    }

    private void processarAjuste(
            StockMovement movement
    ) {

        this.quantity =
                movement.getQuantity();

        this.cost =
                movement.getTotalCost();
    }

    public BigDecimal custoUnitario() {
        if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return cost.divide(
                quantity,
                4,
                RoundingMode.HALF_EVEN
        );
    }

    private void validarMovimento(StockMovement movement) {

        if (movement == null) {
            throw new HitboxException(
                    "Movimento inválido"
            );
        }

        if (
                movement.getQuantity() == null ||
                        movement.getQuantity()
                                .compareTo(BigDecimal.ZERO) <= 0
        ) {

            throw new HitboxException(
                    "Quantidade inválida"
            );
        }

        if (
                movement.getType() == null
        ) {

            throw new HitboxException(
                    "Tipo movimento inválido"
            );
        }

        if (
                movement.getType() == StockMovementType.ENTRY &&
                        (
                                movement.getTotalCost() == null ||
                                        movement.getTotalCost()
                                                .compareTo(BigDecimal.ZERO) <= 0
                        )
        ) {

            throw new HitboxException(
                    "Custo entrada inválido"
            );
        }
    }

    public boolean possuiCategoria() {
        return this.categoriaId != null;
    }

    public void adicionarCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void addImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean estoqueBaixo() {

        return quantity.compareTo(
                minimumStock
        ) <= 0;
    }

    public boolean estoqueInsuficiente(BigDecimal requiredQuantity) {
        return this.quantity.compareTo(requiredQuantity) <= 0;
    }

    public BigDecimal percentualEstoque() {

        if (
                minimumStock == null ||
                        minimumStock.compareTo(BigDecimal.ZERO) <= 0
        ) {

            return BigDecimal.ZERO;
        }

        return quantity
                .multiply(BigDecimal.valueOf(100))
                .divide(
                        minimumStock,
                        2,
                        RoundingMode.HALF_EVEN
                );
    }
}
