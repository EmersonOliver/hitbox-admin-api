package br.com.hitbox.interfaces.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    private String nome;
    private String documento;
    private String email;
    private String telefone;
    private List<EnderecoClienteRequest> enderecos;
}
