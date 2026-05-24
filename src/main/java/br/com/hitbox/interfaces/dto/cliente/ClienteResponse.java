package br.com.hitbox.interfaces.dto.cliente;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {

    private UUID id;
    private String nome;
    private String documento;
    private String email;
    private String telefone;
    private List<EnderecoClienteResponse> enderecos;
}
