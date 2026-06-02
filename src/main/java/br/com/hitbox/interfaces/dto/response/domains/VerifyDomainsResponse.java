package br.com.hitbox.interfaces.dto.response.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyDomainsResponse {
    private Boolean hasCategorias;
    private Boolean hasClientes;
    private Boolean hasProdutos;
    private Boolean hasServiceOrder;
    private Boolean hasPrecos;
    private Boolean hasInventario;
}
