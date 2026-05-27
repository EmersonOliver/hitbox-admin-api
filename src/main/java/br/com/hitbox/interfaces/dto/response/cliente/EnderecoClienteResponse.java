package br.com.hitbox.interfaces.dto.response.cliente;

import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoClienteResponse {

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

}
