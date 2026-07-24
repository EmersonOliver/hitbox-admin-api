package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "supplier_address", indexes = @Index(name = "idx_supplier_address_company", columnList = "company_id, supplier_id"))
@SequenceGenerator(name = "sq_supplier_address_id", sequenceName = "seq_supplier_address_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class SupplierAddressEntity extends TenantEntity {

    @Id
    @GeneratedValue(generator = "sq_supplier_address_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "supplier_address_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEnderecoCliente tipo;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private Long numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    private String complemento;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SuppliersEntity supplier;
}
