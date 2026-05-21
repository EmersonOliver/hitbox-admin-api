package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.enums.ProductionPriority;
import br.com.hitbox.infra.enums.ProductionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "production")
@SequenceGenerator(name = "sq_production_id", sequenceName = "seq_production_id", allocationSize = 1)
public class ProductionOrder {

    @Id
    @Column(name = "production_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_production_id")
    private Long id;

    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductionStatus status;

    @Enumerated(EnumType.STRING)
    private ProductionPriority priority;

    private Integer quantity;
    private String fileName;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
}
