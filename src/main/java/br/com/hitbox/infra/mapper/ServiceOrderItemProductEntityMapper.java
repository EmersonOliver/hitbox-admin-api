package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.ItemProduct;
import br.com.hitbox.infra.entity.ItemProductEntity;
import br.com.hitbox.infra.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderItemProductEntityMapper {

    public ItemProductEntity toEntity(ItemProduct domain) {

        if (domain == null) {
            return null;
        }

        return ItemProductEntity.builder()
                .id(domain.getId())
                .product(
                        domain.getProductId() != null
                                ? ProductEntity.builder()
                                  .id(domain.getProductId())
                                  .build()
                                : null
                )
                .quantity(domain.getQuantity())
                .costUnit(domain.getCostUnit())
                .totalItemCost(domain.getTotalItemCost())
                .salePriceUnit(domain.getSalePriceUnit())
                .totalSalePrice(domain.getTotalSalePrice())
                .estimatedMinutes(domain.getEstimatedMinutes())
                .build();
    }

    public ItemProduct toDomain(ItemProductEntity entity) {

        if (entity == null) {
            return null;
        }

        return ItemProduct.builder()
                .id(entity.getId())
                .serviceOrderId(
                        entity.getServiceOrder() != null
                                ? entity.getServiceOrder().getId()
                                : null
                )
                .productId(
                        entity.getProduct() != null
                                ? entity.getProduct().getId()
                                : null
                )
                .product(
                        entity.getProduct() != null
                                ? br.com.hitbox.core.domain.Product.builder()
                                  .productId(entity.getProduct().getId())
                                  .name(entity.getProduct().getName())
                                  .imageUrl(entity.getProduct().getImageUrl())
                                  .build()
                                : null
                )
                .quantity(entity.getQuantity())
                .costUnit(entity.getCostUnit())
                .totalItemCost(entity.getTotalItemCost())
                .salePriceUnit(entity.getSalePriceUnit())
                .totalSalePrice(entity.getTotalSalePrice())
                .estimatedMinutes(entity.getEstimatedMinutes())
                .build();
    }
}