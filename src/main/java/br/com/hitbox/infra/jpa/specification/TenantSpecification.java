package br.com.hitbox.infra.jpa.specification;

import br.com.hitbox.infra.config.filter.TenantContext;
import org.springframework.data.jpa.domain.Specification;

public final  class TenantSpecification {
    public static <T> Specification<T> currentTenant() {

        return (root, query, cb) ->
                cb.equal(
                        root.get("companyId"),
                        TenantContext.getCompanyId()
                );
    }
}
