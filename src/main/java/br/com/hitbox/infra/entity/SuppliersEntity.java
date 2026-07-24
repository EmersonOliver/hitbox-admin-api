package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
@SequenceGenerator(name = "sq_supplier_id", sequenceName = "seq_supplier_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class SuppliersEntity extends TenantEntity {

    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_supplier_id")
    private Long id;

    private String name;

    private String document;

    private String email;

    private String phone;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "supplier_category_id")
    private SupplierCategoryEntity category;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierAddressEntity> supplierAddress;



}
