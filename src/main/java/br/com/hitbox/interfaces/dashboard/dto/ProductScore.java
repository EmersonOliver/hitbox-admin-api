package br.com.hitbox.interfaces.dashboard.dto;

import br.com.hitbox.core.domain.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductScore {

    private Product product;
    private Integer score;
}
