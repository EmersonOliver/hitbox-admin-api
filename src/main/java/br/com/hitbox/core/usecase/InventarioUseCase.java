package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.domain.StockMovement;
import br.com.hitbox.core.gateway.CategoriaGateway;
import br.com.hitbox.core.gateway.InventarioGateway;
import br.com.hitbox.core.gateway.StorageGateway;
import br.com.hitbox.infra.enums.StockMovementType;
import br.com.hitbox.infra.enums.TipoCategoria;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.query.InventarioQueryService;
import br.com.hitbox.interfaces.dto.InventoryResponse;
import br.com.hitbox.interfaces.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InventarioUseCase {

    private final InventarioGateway gateway;
    private final CategoriaGateway categoriaGateway;
    private final StorageGateway storageGateway;
    private final InventarioQueryService queryService;

    public InventoryResponse salvar(Inventory inventory, MultipartFile image) {
        validarDuplicidade(inventory);
        validarCategoria(inventory.getCategoriaId());
        uploadImagem(inventory, image);
        gateway.salvar(inventory);
        if (inventory.possuiCategoria()) {
            Categoria categoria = categoriaGateway.buscarPorId(inventory.getCategoriaId());
            inventory.adicionarCategoria(categoria);
        }
        return InventoryMapper.toResponse(inventory);

    }

    public InventoryResponse editar(Inventory inventory, MultipartFile image) {
        validarCategoria(inventory.getCategoriaId());
        validarImagem(image, inventory);
        gateway.atualizar(inventory);
        return InventoryMapper.toResponse(inventory);
    }

    private void validarImagem(MultipartFile image, Inventory inventory) {
        if (inventory.getImageUrl() != null) {
            storageGateway.deleteImagem(inventory.getImageUrl());
        }
        uploadImagem(inventory, image);

    }

    private void validarDuplicidade(Inventory inventory) {

        boolean exists =
                gateway.existsByName(
                        inventory.getName()
                );
        if (exists) {
            throw new HitboxException(
                    "Item já cadastrado"
            );
        }
    }

    private void validarCategoria(Long categoriaId) {
        categoriaGateway.buscarPorId(
                categoriaId
        );
    }

    private void uploadImagem(Inventory inventory, MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return;
        }
        String imageUrl =
                storageGateway.salvarImagem(image, "inventory"
                );
        inventory.addImageUrl(imageUrl);
    }

    public Page<Inventory> page(Pageable pageable, List<Long> idCategorias, String search) {
        return queryService.page(pageable, idCategorias, search);
    }

    public void delete(Long id) {
        var entity = gateway.remover(id);
        storageGateway.deleteImagem(entity.getImageUrl());
    }

    public List<Inventory> listAllByCategoria(TipoCategoria tipoCategoria) {
        return gateway.findAllByCategoria(tipoCategoria);
    }

    private void registrarEntradaInicial(Inventory inventory) {

        if (
                inventory.getQuantity() == null ||
                        inventory.getQuantity().compareTo(BigDecimal.ZERO) <= 0
        ) {
            return;
        }

        if (
                inventory.getCost() == null ||
                        inventory.getCost().compareTo(BigDecimal.ZERO) <= 0
        ) {
            return;
        }

        StockMovement movement =
                StockMovement.builder()
                        .inventory(inventory)
                        .type(StockMovementType.ENTRY)
                        .quantity(inventory.getQuantity())
                        .totalCost(inventory.getCost())
                        .unitCost(
                                inventory.getCost()
                                        .divide(
                                                inventory.getQuantity(),
                                                4,
                                                RoundingMode.HALF_EVEN
                                        )
                        )
                        .observation("Entrada inicial cadastro inventário")
                        .build();

        inventory.addMovement(movement);
    }
}
