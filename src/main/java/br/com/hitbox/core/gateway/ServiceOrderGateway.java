package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.ServiceOrder;
import br.com.hitbox.infra.enums.ServiceOrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceOrderGateway {

    ServiceOrder save(ServiceOrder domain);

    ServiceOrder update(ServiceOrder domain);

    void delete(Long id);

    Optional<ServiceOrder> findById(Long id);

    List<ServiceOrder> findAll();

    List<ServiceOrder> findByStatus(ServiceOrderStatus status);

    List<ServiceOrder> findByCliente(UUID clienteId);

    List<ServiceOrder> findByExpectedDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    boolean existsById(Long id);

    void updateStatus(Long serviceOrderId, ServiceOrderStatus status);
}