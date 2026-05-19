package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.infra.entity.InventoryEntity;

public interface InventarioGateway {

    Inventory salvar(
            Inventory inventory
    );

    Inventory atualizar(
            Inventory inventory
    );

    InventoryEntity remover(Long id);

    boolean existsByName(
            String name
    );

}
