package io.mjoh.operaton.engine.delegate;

import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.operaton.spin.Spin;
import org.operaton.spin.json.SpinJsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.operaton.spin.DataFormats.json;

@Component
public class CoffeeDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> coffeeEntity = restTemplate.getForEntity("http://localhost:9080/coffee", String.class);

        SpinJsonNode spinJsonNode = Spin.S(coffeeEntity.getBody(), json());
        String type = spinJsonNode.prop("type").stringValue();
        Number price = spinJsonNode.prop("price").numberValue();
        String stock = spinJsonNode.prop("inStock").stringValue();

        delegateExecution.setVariable("type", type);
        delegateExecution.setVariable("price", price);
        delegateExecution.setVariable("inStock", stock);

    }
}
