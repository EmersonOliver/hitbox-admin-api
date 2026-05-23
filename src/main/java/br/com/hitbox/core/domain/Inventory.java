package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.InventoryUnit;
import br.com.hitbox.infra.exception.HitboxException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@Builder
public class Inventory {
    private Long id;
    private String name;
    private Long categoriaId;
    private Categoria categoria;
    private BigDecimal quantity;
    private InventoryUnit unit;
    private BigDecimal minimumStock;
    private BigDecimal cost;
    private BigDecimal unitCost;
    private String supplier;
    private String location;
    private String imageUrl;
    private Boolean active;

    public void addImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void alterarQuantidade(BigDecimal quantidade) {
        if (quantidade.compareTo(BigDecimal.ZERO) < 0) {
            throw new HitboxException(
                    "Quantidade inválida"
            );
        }
        this.quantity = quantidade;
    }

    public boolean estoqueBaixo() {
        return quantity.compareTo(
                minimumStock
        ) <= 0;
    }


    public void adicionarCategoria(Categoria categoria) {
        this.categoria = categoria;
        this.categoriaId =
                categoria.getId();
    }

    public boolean possuiCategoria() {
        return categoria != null;
    }

    public BigDecimal percentualEstoque() {
        if (minimumStock == null || minimumStock.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return quantity
                .multiply(BigDecimal.valueOf(100))
                .divide(minimumStock, 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal custoUnitario() {
        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        if (cost == null || cost.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return cost.divide(
                quantity,
                4,
                RoundingMode.HALF_EVEN
        );
    }

}
