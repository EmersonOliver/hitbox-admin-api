package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Cliente;
import br.com.hitbox.core.gateway.ClienteGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataClienteRepository;
import br.com.hitbox.infra.mapper.ClienteEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements ClienteGateway {


    private final SpringDataClienteRepository jpaRepository;
    private final ClienteEntityMapper mapper;

    @Override
    public Cliente salvar(Cliente domain) {
        var entity = mapper.toEntity(domain);
        entity = jpaRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Cliente> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByNomeOrEmailOrTelefone(Cliente domain) {
        return jpaRepository.findByNomeOrEmailOrTelefone(domain.getNome(),
                domain.getEmail(), domain.getTelefone()).map(mapper::toDomain);
    }

    @Override
    public Cliente editar(Cliente domain) {
        var entity = mapper.toEntity(domain);
        entity = jpaRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public void removerCliente(Cliente domain) {
        var entity = jpaRepository.findById(domain.getId())
                .orElseThrow(() -> new HitboxException("Cliente não encontrado para remover!"));
        jpaRepository.delete(entity);
    }
}
