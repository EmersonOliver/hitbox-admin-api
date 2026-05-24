package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.domain.ProductMaterial;
import br.com.hitbox.interfaces.dto.produto.ProductMaterialRequest;
import br.com.hitbox.interfaces.dto.produto.ProductMaterialResponse;
import br.com.hitbox.interfaces.dto.produto.ProductRequest;
import br.com.hitbox.interfaces.dto.produto.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductMapper {

    public Product toDomain(
            ProductRequest request
    ) {

        if (request == null) {
            return null;
        }

        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .categoriaId(
                        request.getCategoryId()
                )
                .productionWeight(
                        request.getProductionWeight()
                )
                .shippingWeight(
                        request.getShippingWeight()
                )
                .width(request.getWidth())
                .height(request.getHeight())
                .depth(request.getDepth())
                .materials(
                        toMaterialDomainList(
                                request.getMaterials()
                        )
                )
                .build();
    }

    public ProductResponse toResponse(
            Product domain
    ) {

        if (domain == null) {
            return null;
        }

        return ProductResponse.builder()
                .productId(domain.getProductId())
                .name(domain.getName())
                .imageUrl(domain.getImageUrl())
                .sku(domain.getSku())
                .categoriaId(domain.getCategoriaId())
                .categoryName(domain.getCategoriaName())
                .description(domain.getDescription())
                .currentCalculatedCost(
                        domain.getCurrentCalculatedCost()
                )
                .productionWeight(
                        domain.getProductionWeight()
                )
                .shippingWeight(
                        domain.getShippingWeight()
                )

                .materials(
                        toMaterialResponseList(
                                domain.getMaterials()
                        )
                )

                .build();
    }

    private List<ProductMaterial> toMaterialDomainList(
            List<ProductMaterialRequest> requests
    ) {

        if (requests == null) {
            return Collections.emptyList();
        }

        return requests.stream()
                .map(this::toMaterialDomain)
                .toList();
    }

    private ProductMaterial toMaterialDomain(
            ProductMaterialRequest request
    ) {

        return ProductMaterial.builder()
                .inventory(
                        Inventory.builder()
                                .id(
                                        request.getInventoryId()
                                )
                                .build()
                )
                .quantity(request.getQuantity())
                .consumptionType(
                        request.getConsumptionType()
                )
                .build();
    }

    private List<ProductMaterialResponse> toMaterialResponseList(
            List<ProductMaterial> materials
    ) {

        if (materials == null) {
            return Collections.emptyList();
        }

        return materials.stream()
                .map(this::toMaterialResponse)
                .toList();
    }

    private ProductMaterialResponse toMaterialResponse(
            ProductMaterial material
    ) {

        return ProductMaterialResponse.builder()
                .productMaterialId(
                        material.getProductMaterialId()
                )
                .inventoryId(
                        material.getInventory()
                                .getId()
                )
                .inventoryName(
                        material.getInventory()
                                .getName()
                )
                .quantity(material.getQuantity())
                .consumptionType(
                        material.getConsumptionType()
                )
                .unitCostSnapshot(
                        material.getUnitCostSnapshot()
                )
                .build();
    }
}
