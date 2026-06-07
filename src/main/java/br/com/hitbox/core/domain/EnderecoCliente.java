package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoCliente {
    private Long id;
    private UUID clienteId;
    private TipoEnderecoCliente tipo;
    private String endereco;
    private String cep;
    private Long numero;
    private String bairro;
    private String cidade;
    private String complemento;
    private String observacoes;
    private UUID companyId;
}
