package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers_category")
@SequenceGenerator(name = "sq_supplier_category_id", sequenceName = "seq_supplier_category_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class SupplierCategoryEntity extends TenantEntity {

    @Id
    @Column(name = "supplier_category_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_supplier_category_id")
    private Long id;

    private String code;

    private String name;

    private String description;

    private Boolean active;

}
