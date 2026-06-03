package br.com.hitbox.infra.entity.tenant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@FilterDef(
        name = "tenantFilter",
        parameters = @ParamDef(
                name = "companyId",
                type = UUID.class
        )
)
public abstract class TenantEntity {

    @Column(name = "company_id", nullable = false,
            updatable = false)
    private UUID companyId;
}
