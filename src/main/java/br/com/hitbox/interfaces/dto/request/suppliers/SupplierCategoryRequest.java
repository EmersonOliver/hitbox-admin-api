package br.com.hitbox.interfaces.dto.request.suppliers;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SupplierCategoryRequest {

    private String code;
    private String name;
    private String description;
    private Boolean active;

}
