package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "endereco_cliente", indexes = @Index(name = "idx_cliente_endereco_company", columnList = "company_id, cliente_id"))
@SequenceGenerator(name = "sq_endereco_cliente_id", sequenceName = "seq_endereco_cliente_id", allocationSize = 1)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class EnderecoClienteEntity extends TenantEntity {

    @Id
    @GeneratedValue(generator = "sq_endereco_cliente_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "endereco_cliente_id", nullable = false)
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
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}
