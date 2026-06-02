package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.domain.ProductMaterial;
import br.com.hitbox.infra.entity.*;
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
                .princingRuleId(entity.getPricingRule().getId())
                .name(entity.getName())
                .sku(entity.getSku())
                .currentSalePrice(entity.getPricingRule().getMinimumPrice())
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
                .estimatedMinutes(entity.getEstimatedMinutes())
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
                        .estimatedMinutes(domain.getEstimatedMinutes())
                        .currentCalculatedCost(
                                domain.getCurrentCalculatedCost()
                        )
                        .pricingRule(PricingRuleEntity.builder()
                                .id(domain.getPrincingRuleId())
                                .minimumPrice(domain.getCurrentSalePrice())
                                .build())
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

    public void toEntityUpdate(
            Product domain,
            ProductEntity entity
    ) {

        if (domain == null || entity == null) {
            return;
        }

        entity.setName(domain.getName());

        entity.setCategoria(
                CategoriaEntity.builder()
                        .id(domain.getCategoriaId())
                        .build()
        );
        entity.setPricingRule(PricingRuleEntity.builder()
                        .id(domain.getPrincingRuleId())
                        .minimumPrice(domain.getCurrentSalePrice())
                .build());

        entity.setImageUrl(domain.getImageUrl());

        /*
         * =====================================================
         * MATERIAIS
         * =====================================================
         */
        entity.setDescription(domain.getDescription());
//        entity.setSku(domain.getSku());
        entity.setProductionWeight(domain.getProductionWeight());
        entity.setShippingWeight(domain.getShippingWeight());
        entity.setWidth(domain.getWidth());
        entity.setHeight(domain.getHeight());
        entity.setDepth(domain.getDepth());
        entity.setEstimatedMinutes(domain.getEstimatedMinutes());
        if (domain.getMaterials() == null) {
            entity.getMaterials().clear();
            return;
        }

        /*
         * Remove materiais que não existem mais
         */
        entity.getMaterials().removeIf(entityMat ->

                domain.getMaterials()
                        .stream()
                        .noneMatch(domainMat ->

                                domainMat.getProductMaterialId() != null &&
                                        domainMat.getProductMaterialId()
                                                .equals(entityMat.getId())
                        )
        );

        /*
         * Atualiza ou adiciona
         */
        for (var domainMat : domain.getMaterials()) {

            ProductMaterialsEntity entityMat =
                    entity.getMaterials()
                            .stream()
                            .filter(mat ->

                                    mat.getId() != null &&
                                            mat.getId()
                                                    .equals(domainMat.getProductMaterialId())
                            )
                            .findFirst()
                            .orElse(null);

            /*
             * NOVO MATERIAL
             */
            if (entityMat == null) {

                entityMat =
                        ProductMaterialsEntity.builder()
                                .product(entity)
                                .build();

                entity.getMaterials()
                        .add(entityMat);
            }

            /*
             * UPDATE
             */
            entityMat.setQuantity(
                    domainMat.getQuantity()
            );

            entityMat.setUnitCostSnapshot(
                    domainMat.getUnitCostSnapshot()
            );

            entityMat.setConsumptionType(
                    domainMat.getConsumptionType()
            );

            entityMat.setInventory(
                    InventoryEntity.builder()
                            .id(domainMat.getInventory().getId())
                            .build()
            );
        }
    }
}
