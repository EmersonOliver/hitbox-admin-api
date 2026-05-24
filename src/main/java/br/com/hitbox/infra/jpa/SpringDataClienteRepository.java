package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, UUID>, JpaSpecificationExecutor<ClienteEntity> {

    @Query("SELECT c FROM ClienteEntity c where c.nome =:nome or c.email=:email or c.telefone=:telefone")
    Optional<ClienteEntity> findByNomeOrEmailOrTelefone(@Param("nome") String nome,
                                                        @Param("email") String email,
                                                        @Param("telefone") String telefone);

}
