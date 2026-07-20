package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.events.LowStockEvent;

public interface LowStockEventGateway {
    void execute(LowStockEvent lowStockEvent);
}
