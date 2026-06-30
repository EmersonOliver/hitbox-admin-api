package br.com.hitbox.core.domain;

import br.com.hitbox.interfaces.dto.request.pricing.PricingRuleRequest;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PricingRuleDraft {

    private UUID userId;
    private UUID companyId;
    private Integer currentStep;
    private Map<String,Object> payload;
    private LocalDateTime updatedAt;
}
