package br.com.hitbox.interfaces.dashboard;

import br.com.hitbox.core.usecase.DashboardUseCase;
import br.com.hitbox.interfaces.dashboard.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardUseCase dashboardUseCase;

    @GetMapping
    public DashboardResponse dashboardResponse() {
        return dashboardUseCase.dashboardResponse();
    }
}
