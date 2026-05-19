package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.PricingRule;
import br.com.hitbox.core.usecase.PricingRuleUseCase;
import br.com.hitbox.interfaces.dto.PricingRuleRequest;
import br.com.hitbox.interfaces.dto.PricingRuleResponse;
import br.com.hitbox.interfaces.mapper.PricingRuleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing-rule")
@RequiredArgsConstructor
public class PricingRuleController {

    private final PricingRuleUseCase useCase;

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody PricingRuleRequest request
    ) {
        var domain =
                PricingRuleMapper.toDomain(request);
        return ResponseEntity.ok(
                useCase.salvar(domain)
        );
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PricingRuleResponse>> getPages(Pageable pageable) {
        var response = useCase.page(pageable).map(PricingRuleMapper::toResponse);
        return ResponseEntity.ok(response);
    }
}