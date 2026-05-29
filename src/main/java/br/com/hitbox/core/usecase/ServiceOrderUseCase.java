package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.ItemProduct;
import br.com.hitbox.core.domain.Product;
import br.com.hitbox.core.domain.ServiceOrder;
import br.com.hitbox.core.gateway.ProductGateway;
import br.com.hitbox.core.gateway.ServiceOrderGateway;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceOrderUseCase {

    private final ServiceOrderGateway gateway;

    private final ProductGateway productGateway;

    /*
     * ==========================================
     * CREATE
     * ==========================================
     */

    public ServiceOrder create(ServiceOrder domain) {

        validate(domain);

        prepareItems(domain);

        domain.setStatus(ServiceOrderStatus.OPEN);

        domain.setCreatedAt(LocalDateTime.now());

        domain.recalculateTotals();

        return gateway.save(domain);
    }

    /*
     * ==========================================
     * UPDATE
     * ==========================================
     */

    public ServiceOrder update(ServiceOrder domain) {

        if (domain.getId() == null) {
            throw new IllegalArgumentException(
                    "ID da ordem de serviço é obrigatório"
            );
        }

        var existing =
                gateway.findById(domain.getId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Ordem de serviço não encontrada"
                                )
                        );

        validate(domain);

        prepareItems(domain);

        existing.setClienteId(domain.getClienteId());
        existing.setExpectedDate(domain.getExpectedDate());
        existing.setObservations(domain.getObservations());
        existing.setItems(domain.getItems());

        if (domain.getStatus() != null) {
            existing.updateStatus(domain.getStatus());
        }

        existing.recalculateTotals();

        return gateway.update(existing);
    }

    /*
     * ==========================================
     * DELETE
     * ==========================================
     */

    public void delete(Long id) {

        if (!gateway.existsById(id)) {

            throw new EntityNotFoundException(
                    "Ordem de serviço não encontrada"
            );
        }

        gateway.delete(id);
    }

    /*
     * ==========================================
     * FINDERS
     * ==========================================
     */

    @Transactional(readOnly = true)
    public List<ServiceOrder> findAll() {

        return gateway.findAll();
    }

    @Transactional(readOnly = true)
    public ServiceOrder findById(Long id) {

        return gateway.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Ordem de serviço não encontrada"
                        )
                );
    }

    @Transactional(readOnly = true)
    public List<ServiceOrder> findByStatus(
            ServiceOrderStatus status
    ) {

        return gateway.findByStatus(status);
    }

    /*
     * ==========================================
     * STATUS FLOW
     * ==========================================
     */

    public ServiceOrder startProduction(Long orderId) {

        var order = findById(orderId);

        order.updateStatus(
                ServiceOrderStatus.IN_PRODUCTION
        );

        return gateway.update(order);
    }

    public ServiceOrder finish(Long orderId) {

        var order = findById(orderId);

        order.updateStatus(
                ServiceOrderStatus.FINISHED
        );

        order.setFinishedAt(LocalDateTime.now());

        return gateway.update(order);
    }

    public ServiceOrder cancel(Long orderId) {

        var order = findById(orderId);

        order.updateStatus(
                ServiceOrderStatus.CANCELED
        );

        return gateway.update(order);
    }

    /*
     * ==========================================
     * BUSINESS RULES
     * ==========================================
     */

    private void validate(ServiceOrder domain) {

        if (domain == null) {

            throw new IllegalArgumentException(
                    "Ordem de serviço inválida"
            );
        }

        if (domain.getClienteId() == null) {

            throw new IllegalArgumentException(
                    "Cliente é obrigatório"
            );
        }

        if (
                domain.getItems() == null ||
                        domain.getItems().isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "A ordem deve possuir itens"
            );
        }
    }

    /*
     * ==========================================
     * PREPARE ITEMS
     * ==========================================
     */

    private void prepareItems(ServiceOrder order) {

        for (ItemProduct item : order.getItems()) {

            Product product =
                    productGateway.findById(
                                    item.getProductId()
                            )
                            .orElseThrow(() ->
                                    new EntityNotFoundException(
                                            "Produto não encontrado"
                                    )
                            );

            item.setProduct(product);

            /*
             * custo unitário atual do produto
             */
            item.setCostUnit(
                    product.getCurrentCalculatedCost()
            );

            /*
             * cálculo do total do item
             */
            BigDecimal totalCost =
                    item.getCostUnit()
                            .multiply(item.getQuantity());

            item.setTotalItemCost(totalCost);

            /*
             * total de venda
             */
            BigDecimal totalSale =
                    item.getSalePriceUnit()
                            .multiply(item.getQuantity());

            item.setTotalSalePrice(totalSale);

            /*
             * fallback minutos
             */
            if (item.getEstimatedMinutes() == null) {

                item.setEstimatedMinutes(BigDecimal.ZERO);
            }
        }
    }
}