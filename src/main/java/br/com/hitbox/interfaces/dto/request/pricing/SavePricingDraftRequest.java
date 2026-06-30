package br.com.hitbox.interfaces.dto.request.pricing;

import lombok.Builder;

import java.util.Map;

@Builder
public record SavePricingDraftRequest(
        Integer currentStep,
        Map<String,Object> payload
) {
}
