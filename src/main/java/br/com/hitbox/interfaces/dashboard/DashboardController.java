package br.com.hitbox.interfaces.dashboard;

import br.com.hitbox.core.usecase.DashboardUseCase;
import br.com.hitbox.core.usecase.ProductDashboardUseCase;
import br.com.hitbox.interfaces.dashboard.dto.DashboardResponse;
import br.com.hitbox.interfaces.dashboard.dto.ProductDashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardUseCase dashboardUseCase;
    private final ProductDashboardUseCase productDashboardUseCase;

    @GetMapping
    public DashboardResponse dashboardResponse() {
        return dashboardUseCase.dashboardResponse();
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDashboardResponse> productDashboard(@PathVariable("id") Long id) {

        var result = productDashboardUseCase.execute(id);
        return ResponseEntity.ok(result);
    }
}
