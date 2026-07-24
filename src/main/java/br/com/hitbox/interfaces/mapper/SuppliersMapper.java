package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.SupplierCategory;
import br.com.hitbox.core.domain.Suppliers;
import br.com.hitbox.core.domain.SuppliersAddress;
import br.com.hitbox.interfaces.dto.request.suppliers.SupplierAddressRequest;
import br.com.hitbox.interfaces.dto.request.suppliers.SupplierRequest;
import br.com.hitbox.interfaces.dto.response.suppliers.SupplierCategoryResponse;
import br.com.hitbox.interfaces.dto.response.suppliers.SupplierResponse;
import org.springframework.stereotype.Component;

@Component
public class SuppliersMapper {


    public Suppliers toDomain(SupplierRequest request) {
        return Suppliers.builder()
                .name(request.getName())
                .contact(request.getPhone())
                .email(request.getEmail())
                .category(SupplierCategory.builder().id(request.getSupplierCategoryId()).build())
                .document(request.getDocument())
                .active(request.getActive())
                .build();
    }

    public SuppliersAddress toDomainAddress(SupplierAddressRequest request) {
        return SuppliersAddress.builder()
                .cep(request.getCep())
                .tipo(request.getTipo())
                .bairro(request.getBairro())
                .observacoes(request.getObservacoes())
                .numero(request.getNumero())
                .complemento(request.getComplemento())
                .endereco(request.getEndereco())
                .cidade(request.getCidade())
                .build();
    }

    public SupplierResponse toResponse(Suppliers domain) {
        return SupplierResponse.builder()
                .category(SupplierCategoryResponse.builder()
                        .code(domain.getCategory().getCode())
                        .name(domain.getCategory().getName())
                        .build())
                .phone(domain.getContact())
                .name(domain.getName())
                .email(domain.getEmail())
                .document(domain.getDocument())
                .active(domain.getActive())
                .build();
    }


}
