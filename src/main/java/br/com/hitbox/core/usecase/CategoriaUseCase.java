package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.core.gateway.CategoriaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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



}
