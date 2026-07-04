package br.com.hitbox.infra.service;

import br.com.hitbox.core.domain.PricingRuleDraft;
import br.com.hitbox.core.gateway.PricingRuleDraftGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PricingRuleDraftService implements PricingRuleDraftGateway {

    private final RedisTemplate<String, PricingRuleDraft> redisTemplate;


    @Override
    public void save(PricingRuleDraft draft) {
        redisTemplate.opsForValue().set(
                buildKey(
                        draft.getUserId(),
                        draft.getCompanyId()
                ),
                draft,
                Duration.ofDays(7)
        );
    }

    @Override
    public Optional<PricingRuleDraft> find(UUID userId, UUID companyId) {
        Object value =
                redisTemplate.opsForValue()
                        .get(
                                buildKey(
                                        userId,
                                        companyId
                                )
                        );

        return Optional.ofNullable(
                (PricingRuleDraft) value
        );
    }

    @Override
    public void delete(UUID userId, UUID companyId) {
        redisTemplate.delete(
                buildKey(
                        userId,
                        companyId
                )
        );
    }
    private String buildKey(
            UUID userId,
            UUID companyId
    ) {

        return "pricing-draft:"
                + companyId
                + ":"
                + userId;
    }
}
