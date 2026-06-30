package br.com.hitbox.core.usecase;

import br.com.hitbox.core.gateway.PricingRuleDraftGateway;
import br.com.hitbox.interfaces.dto.response.pricing.PricingDraftResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoadPricingDraftUseCase {

    private final PricingRuleDraftGateway gateway;

    public PricingDraftResponse execute(
            UUID userId,
            UUID companyId
    ) {

        return gateway.find(
                        userId,
                        companyId
                )
                .map(draft ->

                        PricingDraftResponse.builder()

                                .currentStep(
                                        draft.getCurrentStep()
                                )

                                .payload(
                                        draft.getPayload()
                                )

                                .build()
                )
                .orElse(null);
    }
}
