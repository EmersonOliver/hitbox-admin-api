package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Inventory;
import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.domain.ProductMaterial;
import br.com.hitbox.core.gateway.CategoriaGateway;
import br.com.hitbox.core.gateway.InventarioGateway;
import br.com.hitbox.core.gateway.ProductGateway;
import br.com.hitbox.core.gateway.StorageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductUseCase {

    private final CategoriaGateway categoriaGateway;
    private final InventarioGateway inventarioGateway;
    private final ProductGateway productGateway;
    private final StorageGateway storageGateway;

    public Product execute(Product domain, MultipartFile image) {
        uploadImagem(domain, image);
        calculateCurrentCost(domain);
        generateSku(domain);
        return productGateway.salvar(domain);
    }

    private void uploadImagem(Product product, MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return;
        }
        String imageUrl =
                storageGateway.salvarImagem(image, "products"
                );
        product.addImageUrl(imageUrl);
    }


    private void calculateCurrentCost(
            Product product
    ) {

        BigDecimal total =
                BigDecimal.ZERO;

        for (
                ProductMaterial material :
                product.getMaterials()
        ) {

            Inventory inventory =
                    inventarioGateway.findById(
                            material.getInventory()
                                    .getId()
                    );

            BigDecimal unitCost =
                    inventory.getUnitCost();

            material.setInventory(inventory);

            material.setUnitCostSnapshot(
                    unitCost
            );

            BigDecimal materialCost =
                    unitCost.multiply(
                            material.getQuantity()
                    );

            total =
                    total.add(materialCost);
        }

        product.setPreviousCalculatedCost(
                product.getCurrentCalculatedCost()
        );

        product.setCurrentCalculatedCost(
                total
        );

        product.setPreviousCalculatedAt(
                product.getCalculatedAt()
        );

        product.setCalculatedAt(
                LocalDateTime.now()
        );
    }

    private void generateSku(
            Product product
    ) {

        if (
                product.getSku() != null
                        &&
                        !product.getSku().isBlank()
        ) {

            return;
        }

        String sku =
                "HB-"
                        + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
        product.setSku(sku);
    }


    public Page<Product> listAllPage(Pageable pageable, List<Long> idCategorias, String search) {
        return productGateway.listaAllByPage(pageable, idCategorias, search);
    }
}
