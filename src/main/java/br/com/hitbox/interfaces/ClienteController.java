package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.ClienteUseCase;
import br.com.hitbox.infra.query.ClienteQueryService;
import br.com.hitbox.interfaces.dto.request.cliente.ClienteRequest;
import br.com.hitbox.interfaces.dto.response.cliente.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteUseCase clienteUseCase;
    private final ClienteQueryService clienteQueryService;

    @PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
    @PostMapping("/save")
    public ResponseEntity<ClienteResponse> save(@RequestBody ClienteRequest request) {
        var result = clienteUseCase.save(request);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
    @PutMapping("/edit")
    public ResponseEntity<ClienteResponse> edit(@RequestBody ClienteRequest request,
                                                @RequestParam("clienteId") UUID clienteId) {
        var result = clienteUseCase.edit(request, clienteId);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER_VIEW', 'PRODUCTION_VIEW')")
    @GetMapping("page")
    public ResponseEntity<Page<ClienteResponse>> listAllClientes(Pageable pageable, @RequestParam(required = false) String search) {
        return ResponseEntity.ok(clienteQueryService.findBySearch(search,pageable));
    }

    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    @GetMapping("{search}")
    public ResponseEntity<Page<ClienteResponse>> searchCliente(@PathVariable(required = false) String search, Pageable pageable) {
        return ResponseEntity.ok(clienteQueryService.findBySearch(search, pageable));
    }

    @PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable UUID clienteId) {
        clienteUseCase.delete(clienteId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER_VIEW', 'PRODUCTION_VIEW', 'SERVICE_ORDER_VIEW')")
    @GetMapping("findAll")
    public ResponseEntity<List<ClienteResponse>> findAll() {
        var response = clienteQueryService.findAll();
        return ResponseEntity.ok(response);
    }

}
