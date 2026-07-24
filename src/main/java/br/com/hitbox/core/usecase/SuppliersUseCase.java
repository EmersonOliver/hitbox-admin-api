package br.com.hitbox.core.usecase;

import br.com.hitbox.core.gateway.SuppliersGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.interfaces.dto.request.suppliers.SupplierRequest;
import br.com.hitbox.interfaces.mapper.SuppliersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuppliersUseCase {

    private final SuppliersMapper mapper;
    private final SuppliersGateway gateway;


    public void execute(SupplierRequest request) {
        var result = gateway.findByDocumentAndName(request.getDocument(), request.getName());
        if (!result.isEmpty()) {
            throw new HitboxException("Fornecedor já cadastrado na base de dados.");
        }

        var domain = mapper.toDomain(request);
        request.getAddressRequests().forEach(address -> {
            domain.addAddress(mapper.toDomainAddress(address));
        });

        gateway.save(domain);
    }

}
