package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.PricingRuleDraft;
import br.com.hitbox.core.gateway.PricingRuleDraftGateway;
import br.com.hitbox.interfaces.dto.request.pricing.SavePricingDraftRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SavePricingDraftUseCase {
    private final PricingRuleDraftGateway gateway;
    public void execute(
            UUID userId,
            UUID companyId,
            SavePricingDraftRequest request
    ) {

        gateway.save(
                PricingRuleDraft.builder()
                        .userId(userId)
                        .companyId(companyId)
                        .currentStep(request.currentStep())
                        .payload(request.payload())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );
    }
}
