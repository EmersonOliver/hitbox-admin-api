package br.com.hitbox.interfaces.error;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessErrorApi<ID, O> {

    private Integer status;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private ID id;
    private O entidade;

}
