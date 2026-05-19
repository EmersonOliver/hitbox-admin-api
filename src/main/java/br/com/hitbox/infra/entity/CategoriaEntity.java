package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria", uniqueConstraints = {
        @UniqueConstraint(name = "uk_categoria_nome", columnNames = "nome")
})
@SequenceGenerator(name = "sq_categoria_id", sequenceName = "seq_categoria_id", allocationSize = 1)
public class CategoriaEntity {

    @Id
    @Column(name = "categoria_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_categoria_id")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    private String descricao;

    private Boolean ativo;

}
