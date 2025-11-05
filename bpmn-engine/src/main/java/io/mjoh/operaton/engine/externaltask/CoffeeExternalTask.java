package io.mjoh.operaton.engine.externaltask;

import org.operaton.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.operaton.bpm.client.task.ExternalTask;
import org.operaton.bpm.client.task.ExternalTaskHandler;
import org.operaton.bpm.client.task.ExternalTaskService;
import org.operaton.spin.Spin;
import org.operaton.spin.json.SpinJsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.operaton.spin.DataFormats.json;

@Component
@ExternalTaskSubscription(topicName = "coffee")
public class CoffeeExternalTask implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> coffeeEntity = restTemplate.getForEntity("http://localhost:9080/coffee", String.class);

        SpinJsonNode spinJsonNode = Spin.S(coffeeEntity.getBody(), json());
        String type = spinJsonNode.prop("type").stringValue();
        Number price = spinJsonNode.prop("price").numberValue();
        String stock = spinJsonNode.prop("inStock").stringValue();

        externalTaskService.setVariables(externalTask, Map.of("type", type, "price", price, "inStock", stock));

        externalTaskService.complete(externalTask);
    }
}
