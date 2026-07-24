package br.com.hitbox.interfaces.dto.request.suppliers;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {

    private String name;
    private String document;
    private String phone;
    private String email;
    private Long supplierCategoryId;
    private Boolean active;
    private List<SupplierAddressRequest> addressRequests;

}
