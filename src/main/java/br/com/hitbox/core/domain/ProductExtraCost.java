package br.com.hitbox.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductExtraCost {

    private String name;

    private BigDecimal value;

    private boolean multiplyByQuantity;
    private UUID companyId;
}