package br.com.hitbox.interfaces.dto.response.suppliers;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierCategoryResponse {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean active;
}
