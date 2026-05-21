package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.PricingRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpringDataPricingRuleRepository extends JpaRepository<PricingRuleEntity, Long> {


    @Query("select r from PricingRuleEntity r where upper(r.name)=:name and upper(r.salesChannel)=:salesChannel")
    Optional<PricingRuleEntity> findByNameAndChannel(@Param("name") String name,@Param("salesChannel") String salesChannel);

}
