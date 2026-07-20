package br.com.hitbox.infra.message;

import br.com.kafka.util.core.domain.MessageWrapper;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class MessageAbstractWrapper {


    protected MessageWrapper convertMessage(Object object,
                                            Optional<Map<String, String>> optionalMap,
                                            UUID companyId) {
        var message = new MessageWrapper();
        message.setPayload(object);
        message.setKey(companyId.toString());
        Optional.ofNullable(optionalMap)
                .orElse(Optional.empty()).ifPresent(
                        message::setHeader);
        return message;
    }


}
