package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.domain.ProductMaterial;
import br.com.hitbox.infra.entity.InventoryEntity;
import br.com.hitbox.infra.entity.ProductEntity;
import br.com.hitbox.infra.entity.ProductMaterialsEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductEntityMapper {
    public Product toDomain(
            ProductEntity entity
    ) {

        if (entity == null) {
            return null;
        }

        return Product.builder()
                .productId(entity.getId())
                .categoriaId(
                        entity.getCategoria() != null
                                ? entity.getCategoria().getId()
                                : null
                )
                .imageUrl(entity.getImageUrl())
                .name(entity.getName())
                .sku(entity.getSku())
                .description(entity.getDescription())
                .categoriaName(entity.getCategoria().getNome())
                .categoriaId(entity.getCategoria().getId())
                .currentCalculatedCost(
                        entity.getCurrentCalculatedCost()
                )

                .previousCalculatedCost(
                        entity.getPreviousCalculatedCost()
                )

                .productionWeight(
                        entity.getProductionWeight()
                )

                .shippingWeight(
                        entity.getShippingWeight()
                )

                .width(entity.getWidth())
                .height(entity.getHeight())
                .depth(entity.getDepth())

                .calculatedAt(
                        entity.getCalculatedAt()
                )

                .previousCalculatedAt(
                        entity.getPreviousCalculatedAt()
                )

                .materials(
                        toMaterialDomainList(
                                entity.getMaterials()
                        )
                )

                .build();
    }

    public ProductEntity toEntity(
            Product domain
    ) {

        if (domain == null) {
            return null;
        }

        ProductEntity entity =
                ProductEntity.builder()
                        .id(domain.getProductId())
                        .name(domain.getName())
                        .sku(domain.getSku())
                        .description(domain.getDescription())

                        .currentCalculatedCost(
                                domain.getCurrentCalculatedCost()
                        )

                        .previousCalculatedCost(
                                domain.getPreviousCalculatedCost()
                        )

                        .productionWeight(
                                domain.getProductionWeight()
                        )

                        .shippingWeight(
                                domain.getShippingWeight()
                        )

                        .width(domain.getWidth())
                        .height(domain.getHeight())
                        .depth(domain.getDepth())

                        .calculatedAt(
                                domain.getCalculatedAt()
                        )

                        .previousCalculatedAt(
                                domain.getPreviousCalculatedAt()
                        )
                        .imageUrl(domain.getImageUrl())
                        .build();

        entity.setMaterials(
                toMaterialEntityList(
                        domain.getMaterials(),
                        entity
                )
        );

        return entity;
    }

    private List<ProductMaterial> toMaterialDomainList(
            List<ProductMaterialsEntity> entities
    ) {

        if (entities == null) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toMaterialDomain)
                .toList();
    }

    private ProductMaterial toMaterialDomain(
            ProductMaterialsEntity entity
    ) {

        Inventory inventory =
                Inventory.builder()
                        .id(
                                entity.getInventory().getId()
                        )
                        .build();

        return ProductMaterial.builder()
                .productMaterialId(entity.getId())
                .inventory(inventory)
                .quantity(entity.getQuantity())
                .consumptionType(
                        entity.getConsumptionType()
                )
                .unitCostSnapshot(
                        entity.getUnitCostSnapshot()
                )
                .build();
    }

    private List<ProductMaterialsEntity> toMaterialEntityList(
            List<ProductMaterial> domains,
            ProductEntity product
    ) {

        if (domains == null) {
            return Collections.emptyList();
        }

        return domains.stream()
                .map(item ->
                        toMaterialEntity(
                                item,
                                product
                        )
                )
                .toList();
    }

    private ProductMaterialsEntity toMaterialEntity(
            ProductMaterial domain,
            ProductEntity product
    ) {

        InventoryEntity inventory =
                InventoryEntity.builder()
                        .id(
                                domain.getInventory()
                                        .getId()
                        )
                        .build();

        return ProductMaterialsEntity.builder()
                .id(
                        domain.getProductMaterialId()
                )
                .inventory(inventory)
                .quantity(domain.getQuantity())
                .consumptionType(
                        domain.getConsumptionType()
                )
                .unitCostSnapshot(
                        domain.getUnitCostSnapshot()
                )
                .product(product)
                .build();
    }
}
