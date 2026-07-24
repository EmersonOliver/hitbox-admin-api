package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.SuppliersAddress;
import br.com.hitbox.infra.entity.SupplierAddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuppliersAddressEntityMapper {


    public SupplierAddressEntity toEntity(SuppliersAddress domain) {
        return SupplierAddressEntity.builder()
                .id(domain.getId())
                .cep(domain.getCep())
                .tipo(domain.getTipo())
                .numero(domain.getNumero())
                .endereco(domain.getEndereco())
                .observacoes(domain.getObservacoes())
                .bairro(domain.getBairro())
                .cidade(domain.getCidade())
                .complemento(domain.getComplemento())
                .build();
    }

    public SuppliersAddress toDomain(SupplierAddressEntity entity) {
        return SuppliersAddress.builder()
                .id(entity.getId())
                .cep(entity.getCep())
                .tipo(entity.getTipo())
                .numero(entity.getNumero())
                .endereco(entity.getEndereco())
                .observacoes(entity.getObservacoes())
                .bairro(entity.getBairro())
                .cidade(entity.getCidade())
                .complemento(entity.getComplemento())
                .build();

    }
}
