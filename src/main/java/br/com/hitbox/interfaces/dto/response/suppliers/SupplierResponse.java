package br.com.hitbox.interfaces.dto.response.suppliers;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {

    private String name;
    private String document;
    private String phone;
    private String email;
    private Boolean active;
    private SupplierCategoryResponse category;
    private List<SupplierAddressResponse> addressRequests;

}
