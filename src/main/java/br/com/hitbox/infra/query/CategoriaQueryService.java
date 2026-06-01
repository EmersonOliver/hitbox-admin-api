package br.com.hitbox.infra.query;

import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.jpa.SpringDataCategoriaRepository;
import br.com.hitbox.infra.jpa.specification.CategoriaSpecification;
import br.com.hitbox.interfaces.dto.response.categoria.CategoriaResponse;
import br.com.hitbox.interfaces.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaQueryService {

    private final SpringDataCategoriaRepository jpaRepository;

    public Page<CategoriaResponse> categoriaLista(Pageable pageable, String search) {
        Specification<CategoriaEntity> specification = CategoriaSpecification.byNome(search);
        return jpaRepository.findAll(specification, pageable)
                .map(CategoriaMapper::entityToResponse);
    }

    public Long countAll() {
        return jpaRepository.count();
    }
}
