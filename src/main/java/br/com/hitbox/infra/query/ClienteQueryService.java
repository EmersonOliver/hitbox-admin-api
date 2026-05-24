package br.com.hitbox.infra.query;

import br.com.hitbox.infra.entity.ClienteEntity;
import br.com.hitbox.infra.jpa.SpringDataClienteRepository;
import br.com.hitbox.infra.jpa.specification.ClienteSpecification;
import br.com.hitbox.infra.mapper.ClienteEntityMapper;
import br.com.hitbox.interfaces.dto.cliente.ClienteResponse;
import br.com.hitbox.interfaces.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteQueryService {

    private final SpringDataClienteRepository jpaRepository;
    private final ClienteEntityMapper mapper;
    private final ClienteMapper responseMapper;


    public Page<ClienteResponse> listAllClientes(Pageable pageable) {
        var entityResultDomain = jpaRepository.findAll(pageable).map(mapper::toDomain);
        return entityResultDomain.map(responseMapper::domainToResponseCliente);
    }

    public Page<ClienteResponse> findBySearch(String search, Pageable pageable) {
        Specification<ClienteEntity> specs = new ClienteSpecification().specs(search);
        var result = jpaRepository.findAll(specs, pageable).map(mapper::toDomain);
        return result.map(responseMapper::domainToResponseCliente);
    }
}
