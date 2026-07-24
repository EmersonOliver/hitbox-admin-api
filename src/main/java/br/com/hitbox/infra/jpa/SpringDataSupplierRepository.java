package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.SuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpringDataSupplierRepository extends JpaRepository<SuppliersEntity, Long>, JpaSpecificationExecutor<SuppliersEntity> {
}
