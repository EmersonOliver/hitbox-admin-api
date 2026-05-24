package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteGateway {

    Cliente salvar(Cliente domain);

    Optional<Cliente> findById(UUID id);

    Optional<Cliente> findByNomeOrEmailOrTelefone(Cliente domain);

    Cliente editar(Cliente domain);

    void removerCliente(Cliente domain);


}
