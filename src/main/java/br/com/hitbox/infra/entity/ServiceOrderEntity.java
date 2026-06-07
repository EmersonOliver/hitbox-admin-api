package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.entity.tenant.TenantEntity;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "service_order")
@SequenceGenerator(
        name = "sq_service_order_id",
        sequenceName = "seq_service_order_id",
        allocationSize = 1
)
@Filter(
        name = "tenantFilter",
        condition = "company_id = :companyId"
)
public class ServiceOrderEntity extends TenantEntity {

    @Id
    @Column(name = "service_order_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_service_order_id"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @Builder.Default
    @OneToMany(
            mappedBy = "serviceOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemProductEntity> items =
            new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private ServiceOrderStatus status;

    @Column(precision = 19, scale = 2)
    private BigDecimal totalSalePrice;

    @Column(precision = 19, scale = 2)
    private BigDecimal totalProfit;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime expectedDate;

    private LocalDateTime finishedAt;

    @Column(length = 1000)
    private String observations;
}