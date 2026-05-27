package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.ServiceOrder;
import br.com.hitbox.interfaces.dto.request.order.ServiceOrderRequest;
import br.com.hitbox.interfaces.dto.response.order.ServiceOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ServiceOrderMapper {

    private final ServiceOrderItemProductMapper itemMapper;

    public ServiceOrder toDomain(ServiceOrderRequest request) {

        if (request == null) {
            return null;
        }

        return ServiceOrder.builder()
                .id(request.getId())
                .clienteId(request.getClienteId())
                .status(request.getStatus())
                .expectedDate(request.getExpectedDate())
                .observations(request.getObservations())
                .items(
                        request.getItems() == null
                                ? null
                                : request.getItems()
                                  .stream()
                                  .map(itemMapper::toDomain)
                                  .collect(Collectors.toList())
                )
                .build();
    }

    public ServiceOrderResponse toResponse(ServiceOrder domain) {

        if (domain == null) {
            return null;
        }

        return ServiceOrderResponse.builder()
                .id(domain.getId())
                .clienteId(domain.getClienteId())
                .clienteNome(
                        domain.getCliente() != null
                                ? domain.getCliente().getNome()
                                : null
                )
                .status(domain.getStatus())
                .totalSalePrice(domain.getTotalSalePrice())
                .totalProfit(domain.getTotalProfit())
                .createdAt(domain.getCreatedAt())
                .expectedDate(domain.getExpectedDate())
                .finishedAt(domain.getFinishedAt())
                .observations(domain.getObservations())
                .totalEstimatedMinutes(domain.getTotalEstimatedMinutes())
                .items(
                        domain.getItems() == null
                                ? null
                                : domain.getItems()
                                  .stream()
                                  .map(itemMapper::toResponse)
                                  .collect(Collectors.toList())
                )
                .build();
    }
}