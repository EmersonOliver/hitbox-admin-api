package br.com.hitbox.interfaces.dto.request.suppliers;

import br.com.hitbox.infra.enums.TipoEnderecoCliente;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierAddressRequest {

    private String endereco;
    private TipoEnderecoCliente tipo;
    private String cep;
    private Long numero;
    private String bairro;
    private String cidade;
    private String complemento;
    private String observacoes;
}
