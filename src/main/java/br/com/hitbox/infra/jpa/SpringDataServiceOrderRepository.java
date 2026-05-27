package br.com.hitbox.infra.jpa;

import br.com.hitbox.core.domain.ServiceOrder;
import br.com.hitbox.infra.entity.ServiceOrderEntity;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataServiceOrderRepository extends JpaRepository<ServiceOrderEntity, Long> {

    List<ServiceOrderEntity> findByStatus(
            ServiceOrderStatus status
    );

    List<ServiceOrderEntity> findByClienteId(
            UUID clienteId
    );

    List<ServiceOrderEntity> findByExpectedDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );

}
