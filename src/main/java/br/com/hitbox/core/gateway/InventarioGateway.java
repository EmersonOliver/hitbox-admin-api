package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.infra.entity.InventoryEntity;
import br.com.hitbox.infra.enums.TipoCategoria;

import java.util.List;

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

    List<Inventory> findAllByCategoria(TipoCategoria tipoCategoria);
}
