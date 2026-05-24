package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "endereco_cliente")
@SequenceGenerator(name = "sq_endereco_cliente_id", sequenceName = "seq_endereco_cliente_id", allocationSize = 1)
public class EnderecoClienteEntity {

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
