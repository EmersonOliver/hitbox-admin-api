package br.com.hitbox.interfaces.dto.response.pricing;

import lombok.Builder;

import java.util.Map;

@Builder
public record PricingDraftResponse(
        Integer currentStep,
        Map<String,Object> payload

) {
}
