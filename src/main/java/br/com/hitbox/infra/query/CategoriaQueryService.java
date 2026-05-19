package br.com.hitbox.infra.query;

import br.com.hitbox.infra.jpa.SpringDataCategoriaRepository;
import br.com.hitbox.interfaces.dto.CategoriaResponse;
import br.com.hitbox.interfaces.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaQueryService {

    private final SpringDataCategoriaRepository jpaRepository;

    public Page<CategoriaResponse> categoriaLista(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(CategoriaMapper::entityToResponse);
    }

}
