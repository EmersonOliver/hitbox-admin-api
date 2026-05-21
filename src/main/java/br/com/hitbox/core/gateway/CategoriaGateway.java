package br.com.hitbox.core.gateway;


import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.infra.enums.TipoCategoria;
import br.com.hitbox.interfaces.dto.CategoriaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriaGateway {

    void salvar(Categoria domain);

    void atualizar(Long id, Categoria domain);

    void remover(Long id);

    Categoria buscarPorId(
            Long id
    );

    Page<Categoria> listarTodos(Pageable pageable);


    List<Categoria> listAllCategoriasByType(TipoCategoria tipoCategoria);
}
