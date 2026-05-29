package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrder {

    private Long id;

    private UUID clienteId;

    private Cliente cliente;

    @Builder.Default
    private List<ItemProduct> items =
            new ArrayList<>();

    private ServiceOrderStatus status;

    private BigDecimal totalSalePrice;

    private BigDecimal totalProfit;

    private LocalDateTime createdAt;

    private LocalDateTime expectedDate;

    private LocalDateTime finishedAt;

    private String observations;

    /*
     * =========================
     * BUSINESS METHODS
     * =========================
     */

    public void addItem(ItemProduct item) {

        if (item == null) {
            return;
        }

        items.add(item);

        recalculateTotals();
    }

    public void removeItem(Long itemId) {

        items.removeIf(item ->
                item.getId().equals(itemId));

        recalculateTotals();
    }

    public void updateStatus(ServiceOrderStatus status) {

        this.status = status;

        if (status == ServiceOrderStatus.FINISHED) {
            this.finishedAt = LocalDateTime.now();
        }
    }

    public void recalculateTotals() {

        this.totalSalePrice =
                items.stream()
                        .map(ItemProduct::getTotalSalePrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCost =
                items.stream()
                        .map(ItemProduct::getTotalItemCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalProfit =
                totalSalePrice.subtract(totalCost)
                        .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalEstimatedMinutes() {

        return items.stream()
                .map(ItemProduct::getEstimatedMinutes)
                .filter(minutes -> minutes != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getTotalItems() {
        return items.size();
    }

    public boolean isFinished() {
        return ServiceOrderStatus.FINISHED.equals(status);
    }

    public List<ItemProduct> getItems() {
        return Collections.unmodifiableList(items);
    }
}