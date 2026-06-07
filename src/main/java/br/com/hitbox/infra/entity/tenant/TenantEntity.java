package br.com.hitbox.infra.entity.tenant;

import br.com.hitbox.infra.config.filter.TenantContext;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    @PrePersist
    public void prePersist() {
        if (companyId == null) {
            companyId =
                    TenantContext.getCompanyId();
        }
    }
}
