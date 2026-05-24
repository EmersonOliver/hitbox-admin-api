package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.core.gateway.CategoriaGateway;
import br.com.hitbox.infra.enums.TipoCategoria;
import br.com.hitbox.interfaces.dto.categoria.CategoriaResponse;
import br.com.hitbox.interfaces.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public void salvarCategoria(Categoria categoria) {
        categoriaGateway.salvar(categoria);
    }

    public void atualizarCategoria(Long id, Categoria categoria) {
        categoriaGateway.atualizar(id, categoria);
    }

    public void delete(Long id) {
        categoriaGateway.remover(id);
    }


    public List<CategoriaResponse> listAllCategoriasByType(TipoCategoria tipoCategoria) {
        return categoriaGateway.listAllCategoriasByType(tipoCategoria)
                .stream().map(CategoriaMapper::toResponse)
                .toList();
    }
}
