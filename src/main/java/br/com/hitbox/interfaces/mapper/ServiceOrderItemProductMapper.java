package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.ItemProduct;
import br.com.hitbox.interfaces.dto.request.order.ServiceOrderItemProductRequest;
import br.com.hitbox.interfaces.dto.response.order.ServiceOrderItemProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderItemProductMapper {

    public ItemProduct toDomain(ServiceOrderItemProductRequest request) {

        if (request == null) {
            return null;
        }

        return ItemProduct.builder()
                .id(request.getId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .salePriceUnit(request.getSalePriceUnit())
                .estimatedMinutes(request.getEstimatedMinutes())
                .build();
    }

    public ServiceOrderItemProductResponse toResponse(ItemProduct domain) {

        if (domain == null) {
            return null;
        }

        return ServiceOrderItemProductResponse.builder()
                .id(domain.getId())
                .productId(domain.getProductId())
                .productName(
                        domain.getProduct() != null
                                ? domain.getProduct().getName()
                                : null
                )
                .productImage(
                        domain.getProduct() != null
                                ? domain.getProduct().getImageUrl()
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
}