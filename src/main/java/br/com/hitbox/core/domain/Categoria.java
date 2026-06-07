package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.TipoCategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Long id;
    private String nome;
    private TipoCategoria tipo;
    private String descricao;
    private Boolean ativo;
    private UUID companyId;


}
