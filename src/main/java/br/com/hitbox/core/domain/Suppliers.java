package br.com.hitbox.core.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Suppliers {

    private Long supplierId;
    private UUID companyId;
    private String name;
    private String email;
    private String contact;
    private String document;
    private Boolean active;
    private SupplierCategory category;

    @Builder.Default
    private List<SuppliersAddress> address = new ArrayList<>();

    public void addAddress(SuppliersAddress domain){
        address.add(domain);
    }


}
