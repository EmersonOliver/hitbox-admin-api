package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.Cliente;
import br.com.hitbox.core.domain.EnderecoCliente;
import br.com.hitbox.interfaces.dto.request.cliente.ClienteRequest;
import br.com.hitbox.interfaces.dto.response.cliente.ClienteResponse;
import br.com.hitbox.interfaces.dto.request.cliente.EnderecoClienteRequest;
import br.com.hitbox.interfaces.dto.response.cliente.EnderecoClienteResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {


    public Cliente toDomain(ClienteRequest request) {
        return Cliente.builder()
                .email(request.getEmail())
                .nome(request.getNome())
                .telefone(request.getTelefone())
                .documento(request.getDocumento())
                .enderecos(toEnderecoClienteDomainList(request.getEnderecos()))
                .build();
    }

    private List<EnderecoCliente> toEnderecoClienteDomainList(List<EnderecoClienteRequest> enderecos) {
        return enderecos.stream().map(item -> EnderecoCliente
                        .builder()
                        .cidade(item.getCidade())
                        .bairro(item.getBairro())
                        .tipo(item.getTipo())
                        .endereco(item.getEndereco())
                        .complemento(item.getComplemento())
                        .observacoes(item.getObservacoes())
                        .cep(item.getCep())
                        .numero(item.getNumero())
                        .build())

                .toList();
    }

    public ClienteResponse domainToResponseCliente(Cliente domain) {
        return ClienteResponse.builder()
                .id(domain.getId())
                .telefone(domain.getTelefone())
                .email(domain.getEmail())
                .nome(domain.getNome())
                .documento(domain.getDocumento())
                .enderecos(toEnderecoClienteResponseList(domain.getEnderecos()))
                .build();
    }

    private List<EnderecoClienteResponse> toEnderecoClienteResponseList(List<EnderecoCliente> enderecos) {
        return enderecos.stream().map(domain ->
                EnderecoClienteResponse.builder()
                        .clienteId(domain.getClienteId())
                        .cidade(domain.getCidade())
                        .numero(domain.getNumero())
                        .endereco(domain.getEndereco())
                        .cep(domain.getCep())
                        .bairro(domain.getBairro())
                        .complemento(domain.getComplemento())
                        .observacoes(domain.getObservacoes())
                        .tipo(domain.getTipo())
                        .id(domain.getId())
                        .build()
        ).toList();
    }
}
