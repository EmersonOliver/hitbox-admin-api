package br.com.hitbox.core.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierCategory {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean active;
}
