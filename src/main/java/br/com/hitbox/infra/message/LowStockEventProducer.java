package br.com.hitbox.infra.message;

import br.com.hitbox.core.domain.events.LowStockEvent;
import br.com.hitbox.core.gateway.LowStockEventGateway;
import br.com.hitbox.infra.service.UserContextService;
import br.com.kafka.util.infra.annotations.ProducerKey;
import br.com.kafka.util.interfaces.send.SendToKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class LowStockEventProducer extends MessageAbstractWrapper implements LowStockEventGateway {


    @ProducerKey(value = "low-stock")
    private SendToKafka producer;

    private final UserContextService userContextService;
    private final Map<String, String> headersMap = new HashMap<>();

    @Override
    public void execute(LowStockEvent lowStockEvent) {
        headersMap.put("ce_type", "low-stock-v1");
        headersMap.put("x_company", userContextService.getCompanyId().toString());
        var message = convertMessage(lowStockEvent,
                Optional.of(headersMap),
                userContextService.getCompanyId());
        producer.sendMessageToTopic(List.of(message));
    }
}
