package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.StockMovementUseCase;
import br.com.hitbox.interfaces.dto.StockMovementRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory/movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementUseCase useCase;

    @PostMapping("/{inventoryId}")
    public ResponseEntity<Void> movimentar(
            @PathVariable Long inventoryId,
            @RequestBody StockMovementRequest request) {
        useCase.movimentar(
                inventoryId,
                request.getType(),
                request.getQuantity(),
                request.getTotalCost(),
                request.getObservation()
        );
        return ResponseEntity.ok().build();
    }
}
