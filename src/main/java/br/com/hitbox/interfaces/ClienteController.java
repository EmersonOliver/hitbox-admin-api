package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.ClienteUseCase;
import br.com.hitbox.infra.query.ClienteQueryService;
import br.com.hitbox.interfaces.dto.cliente.ClienteRequest;
import br.com.hitbox.interfaces.dto.cliente.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteUseCase clienteUseCase;
    private final ClienteQueryService clienteQueryService;

    @PostMapping("/save")
    public ResponseEntity<ClienteResponse> save(@RequestBody ClienteRequest request) {
        var result = clienteUseCase.save(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<ClienteResponse> edit(@RequestBody ClienteRequest request,
                                                @RequestParam("clienteId") UUID clienteId) {
        var result = clienteUseCase.edit(request, clienteId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("page")
    public ResponseEntity<Page<ClienteResponse>> listAllClientes(Pageable pageable) {
        return ResponseEntity.ok(clienteQueryService.listAllClientes(pageable));
    }

    @GetMapping("{search}")
    public ResponseEntity<Page<ClienteResponse>> searchCliente(@PathVariable String search, Pageable pageable) {
        return ResponseEntity.ok(clienteQueryService.findBySearch(search, pageable));
    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable UUID clienteId) {
        clienteUseCase.delete(clienteId);
        return ResponseEntity.ok().build();
    }

}
