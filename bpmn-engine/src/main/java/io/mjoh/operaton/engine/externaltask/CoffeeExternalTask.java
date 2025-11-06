package io.mjoh.operaton.engine.externaltask;

import org.operaton.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.operaton.bpm.client.task.ExternalTask;
import org.operaton.bpm.client.task.ExternalTaskHandler;
import org.operaton.bpm.client.task.ExternalTaskService;
import org.operaton.spin.Spin;
import org.operaton.spin.json.SpinJsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

import static org.operaton.spin.DataFormats.json;

@Component
@ExternalTaskSubscription(topicName = "coffee")
public class CoffeeExternalTask implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        var coffeePayload = RestClient.builder().baseUrl("http://localhost:9080/coffee").build().get().retrieve().toEntity(String.class).getBody();

        SpinJsonNode spinJsonNode = Spin.S(coffeePayload, json());
        String type = spinJsonNode.prop("type").stringValue();
        Number price = spinJsonNode.prop("price").numberValue();
        String stock = spinJsonNode.prop("inStock").stringValue();

        if (!stock.equals("in stock")) {
            externalTaskService.handleBpmnError(externalTask, "newCoffeeError", "no coffee");
            return;
        }

        externalTaskService.setVariables(externalTask, Map.of("type", type, "price", price, "inStock", stock));

        externalTaskService.complete(externalTask);
    }
}
