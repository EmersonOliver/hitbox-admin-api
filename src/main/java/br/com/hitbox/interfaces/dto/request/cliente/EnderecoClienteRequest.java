package br.com.hitbox.interfaces.dto.request.cliente;

import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoClienteRequest {

    private TipoEnderecoCliente tipo;
    private String endereco;
    private String cep;
    private Long numero;
    private String bairro;
    private String cidade;
    private String complemento;
    private String observacoes;
}
