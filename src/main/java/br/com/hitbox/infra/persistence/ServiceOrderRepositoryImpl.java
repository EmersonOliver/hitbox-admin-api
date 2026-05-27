package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.ServiceOrder;
import br.com.hitbox.core.gateway.ServiceOrderGateway;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import br.com.hitbox.infra.jpa.SpringDataServiceOrderRepository;
import br.com.hitbox.infra.mapper.ServiceOrderEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ServiceOrderRepositoryImpl implements ServiceOrderGateway {

    private final SpringDataServiceOrderRepository jpaRepository;

    private final ServiceOrderEntityMapper mapper;


    @Override
    public ServiceOrder save(ServiceOrder domain) {

        var entity =
                mapper.toEntity(domain);

        var saved =
                jpaRepository.save(entity);

        return mapper.toDomain(saved);
    }


    @Override
    public ServiceOrder update(ServiceOrder domain) {

        var entity =
                mapper.toEntity(domain);

        var updated =
                jpaRepository.save(entity);

        return mapper.toDomain(updated);
    }


    @Override
    public void delete(Long id) {

        jpaRepository.deleteById(id);
    }


    @Override
    public Optional<ServiceOrder> findById(Long id) {

        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }


    @Override
    public List<ServiceOrder> findAll() {

        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


    @Override
    public List<ServiceOrder> findByStatus(
            ServiceOrderStatus status
    ) {

        return jpaRepository.findByStatus(status)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


    @Override
    public List<ServiceOrder> findByCliente(
            UUID clienteId
    ) {

        return jpaRepository.findByClienteId(clienteId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


    @Override
    public List<ServiceOrder> findByExpectedDateBetween(
            LocalDateTime start,
            LocalDateTime end
    ) {

        return jpaRepository
                .findByExpectedDateBetween(start, end)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


    @Override
    public boolean existsById(Long id) {

        return jpaRepository.existsById(id);
    }
}
