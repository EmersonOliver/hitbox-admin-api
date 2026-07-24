package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersAddress {

    private Long id;
    private TipoEnderecoCliente tipo;
    private String endereco;
    private String cep;
    private Long numero;
    private String bairro;
    private String cidade;
    private String complemento;
    private String observacoes;

}
