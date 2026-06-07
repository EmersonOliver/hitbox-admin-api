package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Cliente;
import br.com.hitbox.core.domain.ServiceOrder;
import br.com.hitbox.infra.entity.ClienteEntity;
import br.com.hitbox.infra.entity.ServiceOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ServiceOrderEntityMapper {

    private final ServiceOrderItemProductEntityMapper itemMapper;

    public ServiceOrderEntity toEntity(ServiceOrder domain) {

        if (domain == null) {
            return null;
        }

        ServiceOrderEntity entity =
                ServiceOrderEntity.builder()
                        .id(domain.getId())
                        .cliente(
                                domain.getClienteId() != null
                                        ? ClienteEntity.builder()
                                          .id(domain.getClienteId())
                                          .build()
                                        : null
                        )
                        .companyId(domain.getCompanyId())
                        .status(domain.getStatus())
                        .totalSalePrice(domain.getTotalSalePrice())
                        .totalProfit(domain.getTotalProfit())
                        .createdAt(domain.getCreatedAt())
                        .expectedDate(domain.getExpectedDate())
                        .finishedAt(domain.getFinishedAt())
                        .observations(domain.getObservations())
                        .build();

        if (domain.getItems() != null) {

            entity.setItems(
                    domain.getItems()
                            .stream()
                            .map(item -> {

                                var itemEntity =
                                        itemMapper.toEntity(item);

                                itemEntity.setServiceOrder(entity);
                                itemEntity.setCompanyId(entity.getCompanyId());

                                return itemEntity;
                            })
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }

    public ServiceOrder toDomain(ServiceOrderEntity entity) {

        if (entity == null) {
            return null;
        }

        return ServiceOrder.builder()
                .id(entity.getId())
                .clienteId(
                        entity.getCliente() != null
                                ? entity.getCliente().getId()
                                : null
                )
                .cliente(Cliente.builder().nome(entity.getCliente()
                        .getNome()).build())
                .status(entity.getStatus())
                .totalSalePrice(entity.getTotalSalePrice())
                .totalProfit(entity.getTotalProfit())
                .createdAt(entity.getCreatedAt())
                .expectedDate(entity.getExpectedDate())
                .finishedAt(entity.getFinishedAt())
                .observations(entity.getObservations())
                .items(
                        entity.getItems() == null
                                ? null
                                : entity.getItems()
                                  .stream()
                                  .map(itemMapper::toDomain)
                                  .collect(Collectors.toList())
                )
                .build();
    }
}