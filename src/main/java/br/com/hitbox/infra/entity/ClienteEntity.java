package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cliente", indexes = @Index(name = "idx_cliente_company", columnList = "company_id"))
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class ClienteEntity extends TenantEntity {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String documento;

    private String email;

    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoClienteEntity> enderecos;

}
