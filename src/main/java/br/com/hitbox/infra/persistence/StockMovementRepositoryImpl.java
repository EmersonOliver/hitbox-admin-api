package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.StockMovement;
import br.com.hitbox.core.gateway.StockMovementGateway;
import br.com.hitbox.infra.jpa.SpringDataStockMovement;
import br.com.hitbox.infra.mapper.StockMovementEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockMovementRepositoryImpl implements StockMovementGateway {

    private final SpringDataStockMovement jpaRepository;
    private final StockMovementEntityMapper mapper;


    @Override
    public void salvar(StockMovement movement) {
        jpaRepository.save(mapper.toEntity(movement));

    }
}
