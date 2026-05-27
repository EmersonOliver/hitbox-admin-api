package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.ServiceOrderUseCase;
import br.com.hitbox.interfaces.dto.request.order.ServiceOrderRequest;
import br.com.hitbox.interfaces.dto.response.order.ServiceOrderResponse;
import br.com.hitbox.interfaces.mapper.ServiceOrderMapper;
import br.com.hitbox.infra.enums.ServiceOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service-order")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderUseCase useCase;

    private final ServiceOrderMapper mapper;

    @PostMapping("create")
    public ResponseEntity<ServiceOrderResponse> create(
            @RequestBody ServiceOrderRequest request
    ) {

        var domain =
                mapper.toDomain(request);

        var result =
                useCase.create(domain);

        return ResponseEntity.ok(
                mapper.toResponse(result)
        );
    }

    @PutMapping("update/{orderId}")
    public ResponseEntity<ServiceOrderResponse> update(
            @PathVariable("orderId") Long orderId,
            @RequestBody ServiceOrderRequest request
    ) {

        var domain =
                mapper.toDomain(request);

        domain.setId(orderId);

        var result =
                useCase.update(domain);

        return ResponseEntity.ok(
                mapper.toResponse(result)
        );
    }

    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<Void> delete(
            @PathVariable("orderId") Long orderId
    ) {

        useCase.delete(orderId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("find-all")
    public ResponseEntity<List<ServiceOrderResponse>> findAll() {

        var response =
                useCase.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("find-by-id/{orderId}")
    public ResponseEntity<ServiceOrderResponse> findById(
            @PathVariable("orderId") Long orderId
    ) {

        var result =
                useCase.findById(orderId);

        return ResponseEntity.ok(
                mapper.toResponse(result)
        );
    }


    @GetMapping("find-by-status/{status}")
    public ResponseEntity<List<ServiceOrderResponse>> findByStatus(
            @PathVariable("status") ServiceOrderStatus status
    ) {

        var response =
                useCase.findByStatus(status)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("start-production/{orderId}")
    public ResponseEntity<ServiceOrderResponse> startProduction(
            @PathVariable("orderId") Long orderId
    ) {

        var result =
                useCase.startProduction(orderId);

        return ResponseEntity.ok(
                mapper.toResponse(result)
        );
    }

    @PatchMapping("finish/{orderId}")
    public ResponseEntity<ServiceOrderResponse> finish(
            @PathVariable("orderId") Long orderId
    ) {

        var result =
                useCase.finish(orderId);

        return ResponseEntity.ok(
                mapper.toResponse(result)
        );
    }

    @PatchMapping("cancel/{orderId}")
    public ResponseEntity<ServiceOrderResponse> cancel(
            @PathVariable("orderId") Long orderId
    ) {

        var result =
                useCase.cancel(orderId);

        return ResponseEntity.ok(
                mapper.toResponse(result)
        );
    }
}