package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.StockMovement;

public interface StockMovementGateway {
    void salvar(StockMovement movement);
}
