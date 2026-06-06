package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "sq_categoria_id", sequenceName = "seq_categoria_id", allocationSize = 1)
@Table(name = "categoria", uniqueConstraints = {
        @UniqueConstraint(name = "uk_categoria_nome", columnNames = {"company_id","nome"}),
}, indexes = {
        @Index(name = "idx_categoria_company", columnList = "company_id,nome")
})
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class CategoriaEntity extends TenantEntity {

    @Id
    @Column(name = "categoria_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_categoria_id")
    private Long id;

    @Column(nullable = false,length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    private String descricao;

    private Boolean ativo;


}
