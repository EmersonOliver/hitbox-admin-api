package br.com.hitbox.interfaces.dto.response.inventario;

import br.com.hitbox.core.domain.Inventory;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventarioInsuficienteResponse {

    private Boolean valid;
    private Long id;
    private String message;
    private Inventory inventory;
}
