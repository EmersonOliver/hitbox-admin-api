package br.com.hitbox.interfaces;

import br.com.hitbox.infra.query.VerifyDomainsQueryService;
import br.com.hitbox.interfaces.dto.response.domains.VerifyDomainsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("verify/domains")
@RequiredArgsConstructor
public class VerifyDomainDataIntegrationController {

    private final VerifyDomainsQueryService queryService;

    @GetMapping("all")
    public ResponseEntity<VerifyDomainsResponse> verifyDomains() {
        var result = queryService.verifyDomainsResponse();
        return ResponseEntity.ok(result);
    }


}
