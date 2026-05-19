package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataInventarioRepository extends JpaRepository<InventoryEntity, Long> {

    boolean existsByName(String name);
}
