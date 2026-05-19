package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Categoria;
import br.com.hitbox.core.gateway.CategoriaGateway;
import br.com.hitbox.infra.entity.CategoriaEntity;
import br.com.hitbox.infra.exception.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataCategoriaRepository;
import br.com.hitbox.infra.mapper.CategoriaEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaRepositoryImpl implements CategoriaGateway {

    private final SpringDataCategoriaRepository jpaRepository;


    @Override
    public void salvar(Categoria domain) {
        jpaRepository.save(CategoriaEntityMapper.toEntity(domain));
    }

    @Override
    public void atualizar(Long id, Categoria domain) {
        CategoriaEntity entity = jpaRepository
                .findById(id).orElseThrow(() -> new HitboxException("Categoria não encontrada!"));
        var exists = jpaRepository.findByNome(domain.getNome().toUpperCase());
        if (exists.isPresent() && !exists.get().getId().equals(id)) {
            throw new HitboxException(
                    "Já existe uma categoria com este nome!"
            );
        }
        entity.setAtivo(domain.getAtivo());
        entity.setDescricao(domain.getDescricao());
        entity.setNome(domain.getNome());
        entity.setTipo(domain.getTipo());
        jpaRepository.save(entity);
    }

    @Override
    public void remover(Long id) {
        var entity = jpaRepository.findById(id)
                .orElseThrow(() -> new HitboxException("Ocorreu uma falha para localizar a categoria!"));
        jpaRepository.delete(entity);
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(CategoriaEntityMapper::toDomain)
                .orElseThrow(()-> new HitboxException("Falha ao buscar categoria por id!"));
    }

    @Override
    public Page<Categoria> listarTodos(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(CategoriaEntityMapper::toDomain);
    }
}
