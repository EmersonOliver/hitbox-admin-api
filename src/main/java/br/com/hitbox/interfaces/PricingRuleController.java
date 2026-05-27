package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.ProductPricingContext;
import br.com.hitbox.core.usecase.PricingRuleUseCase;
import br.com.hitbox.interfaces.dto.request.pricing.PricingRuleRequest;
import br.com.hitbox.interfaces.dto.response.pricing.PricingRuleResponse;
import br.com.hitbox.interfaces.dto.response.pricing.SuggestedPriceResult;
import br.com.hitbox.interfaces.mapper.PricingRuleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/edit")
    public ResponseEntity<?> save(
            @RequestParam Long ruleId,
            @RequestBody PricingRuleRequest request
    ) {
        var domain =
                PricingRuleMapper.toDomain(request);
        domain.setId(ruleId);
        return ResponseEntity.ok(
                useCase.editar(domain)
        );
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PricingRuleResponse>> getPages(Pageable pageable) {
        var response = useCase.page(pageable).map(PricingRuleMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{ruleId}")
    public ResponseEntity<Void> deletePricingRule(@PathVariable("ruleId") Long ruleId) {
        useCase.deleteRule(ruleId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("suggested/price")
    public ResponseEntity<List<SuggestedPriceResult>> suggestedPriceResultResponse(@RequestBody ProductPricingContext context) {
        return ResponseEntity.ok(useCase.suggestedPriceRule(context));
    }

    @GetMapping("findAll")
    public ResponseEntity<List<PricingRuleResponse>> findAllRules() {
        var response = useCase.findAllRules().stream().map(PricingRuleMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("ruleById/{ruleId}")
    public ResponseEntity<SuggestedPriceResult> getSuggestedPrice(@PathVariable Long ruleId,
                                                                  @RequestBody ProductPricingContext context) {
        var result = useCase.suggestedPriceRule(context)
                .stream()
                .filter(rs -> rs.getRuleId().equals(ruleId))
                .findFirst().orElse(null);
        return ResponseEntity.ok(result);

    }
}