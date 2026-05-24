package br.com.hitbox.core.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private UUID id;
    private String nome;
    private String documento;
    private String email;
    private String telefone;

    @Builder.Default
    private List<EnderecoCliente> enderecos = new ArrayList<>();

    public void addEndereco(EnderecoCliente enderecoCliente) {
        enderecos.add(enderecoCliente);
    }

    public void removerEndereco(EnderecoCliente enderecoCliente) {
        enderecos.remove(enderecoCliente);
    }
}
