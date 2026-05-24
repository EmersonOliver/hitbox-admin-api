package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Cliente;
import br.com.hitbox.core.gateway.ClienteGateway;
import br.com.hitbox.infra.entity.EnderecoClienteEntity;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataClienteRepository;
import br.com.hitbox.infra.mapper.ClienteEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements ClienteGateway {


    private final SpringDataClienteRepository jpaRepository;
    private final ClienteEntityMapper mapper;

    @Override
    public Cliente salvar(Cliente domain) {
        var entity = mapper.toEntity(domain);
        entity = jpaRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Cliente> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByNomeOrEmailOrTelefone(Cliente domain) {
        return jpaRepository.findByNomeOrEmailOrTelefone(domain.getNome(),
                domain.getEmail(), domain.getTelefone()).map(mapper::toDomain);
    }

    @Override
    public Cliente editar(Cliente domain) {

        var entity =
                jpaRepository.findById(domain.getId())
                        .orElseThrow(() ->
                                new HitboxException(
                                        "Cliente invalido!"
                                )
                        );

        entity.setNome(
                domain.getNome()
        );

        entity.setDocumento(
                domain.getDocumento()
        );

        entity.setEmail(
                domain.getEmail()
        );

        entity.setTelefone(
                domain.getTelefone()
        );

        /*
         * REMOVE ENDEREÇOS
         * QUE NÃO EXISTEM MAIS
         */

        entity.getEnderecos()
                .removeIf(enderecoEntity ->

                        domain.getEnderecos()
                                .stream()
                                .noneMatch(enderecoDomain ->

                                        enderecoDomain.getId() != null &&
                                                enderecoDomain.getId()
                                                        .equals(
                                                                enderecoEntity.getId()
                                                        )
                                )
                );

        /*
         * ATUALIZA / ADICIONA
         */

        for (
                var enderecoDomain :
                domain.getEnderecos()
        ) {

            /*
             * NOVO ENDEREÇO
             */

            if (
                    enderecoDomain.getId() == null
            ) {

                EnderecoClienteEntity novoEndereco =
                        EnderecoClienteEntity.builder()
                                .cliente(entity)
                                .tipo(enderecoDomain.getTipo())
                                .endereco(enderecoDomain.getEndereco())
                                .cep(enderecoDomain.getCep())
                                .numero(enderecoDomain.getNumero())
                                .bairro(enderecoDomain.getBairro())
                                .cidade(enderecoDomain.getCidade())
                                .complemento(enderecoDomain.getComplemento())
                                .observacoes(enderecoDomain.getObservacoes())
                                .build();

                entity.getEnderecos()
                        .add(novoEndereco);

                continue;
            }

            /*
             * EDITA EXISTENTE
             */

            EnderecoClienteEntity enderecoEntity =
                    entity.getEnderecos()
                            .stream()
                            .filter(e ->
                                    e.getId()
                                            .equals(
                                                    enderecoDomain.getId()
                                            )
                            )
                            .findFirst()
                            .orElseThrow(() ->
                                    new HitboxException(
                                            "Endereço inválido"
                                    )
                            );

            enderecoEntity.setTipo(
                    enderecoDomain.getTipo()
            );

            enderecoEntity.setEndereco(
                    enderecoDomain.getEndereco()
            );

            enderecoEntity.setCep(
                    enderecoDomain.getCep()
            );

            enderecoEntity.setNumero(
                    enderecoDomain.getNumero()
            );

            enderecoEntity.setBairro(
                    enderecoDomain.getBairro()
            );

            enderecoEntity.setCidade(
                    enderecoDomain.getCidade()
            );

            enderecoEntity.setComplemento(
                    enderecoDomain.getComplemento()
            );

            enderecoEntity.setObservacoes(
                    enderecoDomain.getObservacoes()
            );
        }

        entity =
                jpaRepository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public void removerCliente(Cliente domain) {
        var entity = jpaRepository.findById(domain.getId())
                .orElseThrow(() -> new HitboxException("Cliente não encontrado para remover!"));
        jpaRepository.delete(entity);
    }
}
