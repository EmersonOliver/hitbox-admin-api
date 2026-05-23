package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.StockMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataStockMovement extends JpaRepository<StockMovementEntity, Long> {
}
