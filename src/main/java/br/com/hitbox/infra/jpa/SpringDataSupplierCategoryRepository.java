package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.SupplierCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpringDataSupplierCategoryRepository extends JpaRepository<SupplierCategoryEntity, Long>, JpaSpecificationExecutor<SupplierCategoryEntity> {
}
