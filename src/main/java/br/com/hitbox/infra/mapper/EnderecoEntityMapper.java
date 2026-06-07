package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.EnderecoCliente;
import br.com.hitbox.infra.entity.ClienteEntity;
import br.com.hitbox.infra.entity.EnderecoClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class EnderecoEntityMapper {

    private ClienteEntity clienteEntity;

    public EnderecoClienteEntity toEntity(EnderecoCliente domain) {
        return EnderecoClienteEntity.builder()
                .tipo(domain.getTipo())
                .cep(domain.getCep())
                .endereco(domain.getEndereco())
                .bairro(domain.getBairro())
                .cidade(domain.getCidade())
                .complemento(domain.getComplemento())
                .numero(domain.getNumero())
                .observacoes(domain.getObservacoes())
                .cliente(clienteEntity)
                .companyId(domain.getCompanyId())
                .build();
    }


    public EnderecoCliente toDomain(EnderecoClienteEntity entity) {
        return EnderecoCliente.builder()
                .id(entity.getId())
                .endereco(entity.getEndereco())
                .numero(entity.getNumero())
                .observacoes(entity.getObservacoes())
                .cep(entity.getCep())
                .cidade(entity.getCidade())
                .complemento(entity.getComplemento())
                .bairro(entity.getBairro())
                .tipo(entity.getTipo())
                .companyId(entity.getCompanyId())
                .build();
    }

    public void setCliente(ClienteEntity cliente) {
        this.clienteEntity = cliente;

    }

}
