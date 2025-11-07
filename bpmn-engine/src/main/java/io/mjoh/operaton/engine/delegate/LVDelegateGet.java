package io.mjoh.operaton.engine.delegate;

import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component("lvDelegateGet")
public class LVDelegateGet implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ResponseEntity<String> response = RestClient.builder().baseUrl("http://localhost:9080/tkv").build().get().retrieve().toEntity(String.class);
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode.value() == 202) {
            delegateExecution.setVariable("status", "failed");
        } else {
            delegateExecution.setVariable("status", "success");
        }
    }
}