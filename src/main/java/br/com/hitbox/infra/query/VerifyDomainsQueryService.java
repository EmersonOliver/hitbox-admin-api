package br.com.hitbox.infra.query;

import br.com.hitbox.interfaces.dto.response.domains.VerifyDomainsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyDomainsQueryService {

    private final CategoriaQueryService categoriaQueryService;
    private final ClienteQueryService clienteQueryService;
    private final InventarioQueryService inventarioQueryService;
    private final ProductQueryService productQueryService;
    private final ServiceOrderQueryService serviceOrderQueryService;
    private final PricingRuleQueryService princingRuleQueryService;

    public VerifyDomainsResponse verifyDomainsResponse() {
        return VerifyDomainsResponse.builder()
                .hasServiceOrder(contains(serviceOrderQueryService.countAll()))
                .hasCategorias(contains(categoriaQueryService.countAll()))
                .hasProdutos(contains(productQueryService.countAll()))
                .hasClientes(contains(clienteQueryService.countAll()))
                .hasInventario(contains(inventarioQueryService.countAll()))
                .hasPrecos(contains(princingRuleQueryService.countAll()))
                .build();
    }

    private boolean contains(Long result) {
        return result > 0;
    }

}
