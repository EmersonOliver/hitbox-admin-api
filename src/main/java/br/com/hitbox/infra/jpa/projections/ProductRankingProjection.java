package br.com.hitbox.infra.jpa.projections;

import java.math.BigDecimal;

public interface ProductRankingProjection {
    Long getProductId();
    String getProductName();
    BigDecimal getPrice();
    String getImageUrl();
    Long getTotalOrders();
}
