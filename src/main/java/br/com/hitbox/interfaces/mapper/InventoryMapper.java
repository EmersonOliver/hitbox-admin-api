package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.interfaces.dto.InventoryRequest;
import br.com.hitbox.interfaces.dto.InventoryResponse;

import java.math.BigDecimal;

public class InventoryMapper {
    private InventoryMapper() {
    }

    public static Inventory toDomain(InventoryRequest request) {
        return Inventory.builder()
                .name(request.getName())
                .categoriaId(request.getCategoriaId())
                .unit(request.getUnit())
                .minimumStock(request.getMinimumStock())
                .supplier(request.getSupplier())
                .location(request.getLocation())
                .active(request.getActive())
                .build();
    }

    public static InventoryResponse toResponse(Inventory domain) {
        return InventoryResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .category(
                        domain.getCategoria() != null
                                ? domain.getCategoria().getNome()
                                : null
                )
                .categoriaId(
                        domain.getCategoria() != null
                                ? domain.getCategoria().getId()
                                : null
                )
                .quantity(domain.getQuantity())
                .unit(domain.getUnit())
                .minimumStock(domain.getMinimumStock())
                .cost(domain.getCost())
                .unitCost(domain.getUnitCost())
                .supplier(domain.getSupplier())
                .location(domain.getLocation())
                .imageUrl(domain.getImageUrl())
                .active(domain.getActive())
                .build();
    }
}
