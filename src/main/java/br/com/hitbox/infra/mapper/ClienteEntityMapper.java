package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Cliente;
import br.com.hitbox.infra.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteEntityMapper {

    private final EnderecoEntityMapper enderecoMapper;

    public ClienteEntity toEntity(Cliente domain) {
        var entity = ClienteEntity.builder()
                .nome(domain.getNome())
                .email(domain.getEmail())
                .documento(domain.getDocumento())
                .telefone(domain.getTelefone())
                .build();
        var enderecosCliente = domain.getEnderecos().stream().map(enderecoMapper::toEntity).toList();
        entity.setEnderecos(enderecosCliente);
        return entity;
    }

    public Cliente toDomain(ClienteEntity entity) {
        var domain = Cliente.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nome(entity.getNome())
                .telefone(entity.getTelefone())
                .documento(entity.getDocumento())
                .build();
        entity.getEnderecos().forEach(item ->
                domain.addEndereco(enderecoMapper.toDomain(item)));
        return domain;
    }

}
