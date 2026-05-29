package br.com.hitbox.infra.query;

import br.com.hitbox.infra.enums.ServiceOrderStatus;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataServiceOrderRepository;
import br.com.hitbox.infra.mapper.ServiceOrderEntityMapper;
import br.com.hitbox.interfaces.dto.response.order.ServiceOrderResponse;
import br.com.hitbox.interfaces.mapper.ServiceOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOrderQueryService {

    private final SpringDataServiceOrderRepository jpaRepository;

    private final ServiceOrderEntityMapper mapper;
    private final ServiceOrderMapper responseMapper;

    public ServiceOrderResponse getOrderServiceById(Long idOrder) {
        return this.jpaRepository.findById(idOrder)
                .map(mapper::toDomain).map(responseMapper::toResponse).orElseThrow(() -> new HitboxException("Ordem de serviço inexistente!"));
    }

    public List<ServiceOrderResponse> listAllByStatus(ServiceOrderStatus status) {
        return jpaRepository.findByStatus(status).stream().map(mapper::toDomain)
                .map(responseMapper::toResponse).toList();
    }

}
