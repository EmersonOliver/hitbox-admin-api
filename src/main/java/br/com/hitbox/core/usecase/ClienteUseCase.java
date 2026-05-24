package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Cliente;
import br.com.hitbox.core.gateway.ClienteGateway;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.interfaces.dto.cliente.ClienteRequest;
import br.com.hitbox.interfaces.dto.cliente.ClienteResponse;
import br.com.hitbox.interfaces.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClienteUseCase {

    private final ClienteGateway gateway;
    private final ClienteMapper mapper;

    public ClienteResponse save(ClienteRequest request) {
        var domain = mapper.toDomain(request);
        var exists = gateway.findByNomeOrEmailOrTelefone(domain);
        if (exists.isPresent()) {
            throw new HitboxException("Cliente já existe na base de dados!");
        }
        var saved = gateway.salvar(domain);
        return mapper.domainToResponseCliente(saved);
    }

    public ClienteResponse edit(ClienteRequest request, UUID clienteId) {
        validarExistenciaClienteId(clienteId);

        var domain = mapper.toDomain(request);
        var existsCliente = gateway.findByNomeOrEmailOrTelefone(domain);

        if (existsCliente.isPresent() && !existsCliente.get().getId().equals(clienteId)) {
            throw new HitboxException("Já existe um cliente cadastrado na base!");
        }
        var cliente = gateway.editar(domain);

        return mapper.domainToResponseCliente(cliente);
    }

    private void validarExistenciaClienteId(UUID uuid) {
        gateway.findById(uuid)
                .orElseThrow(()
                        -> new HitboxException("Cliente não encontrado!"));
    }

    public void delete(UUID clienteId) {
        gateway.removerCliente(Cliente.builder()
                .id(clienteId)
                .build());
    }
}
