package br.com.hitbox.infra.jpa.dashboard;

import br.com.hitbox.infra.entity.ItemProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductDashboardRepository extends JpaRepository<ItemProductEntity, Long>{
}
