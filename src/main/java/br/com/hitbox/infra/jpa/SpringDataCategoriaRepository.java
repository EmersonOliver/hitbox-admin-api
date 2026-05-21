package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.enums.TipoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataCategoriaRepository extends JpaRepository<CategoriaEntity, Long>, JpaSpecificationExecutor<CategoriaEntity> {

    @Query("select c FROM CategoriaEntity c where upper(c.nome) =:nome")
    Optional<CategoriaEntity> findByNome(@Param("nome") String nome);

    @Query("select c FROM CategoriaEntity c where c.tipo =:tipo")
    List<CategoriaEntity> findByCategoriaType(@Param("tipo") TipoCategoria tipoCategoria);
}
