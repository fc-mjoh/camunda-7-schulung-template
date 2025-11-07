package io.mjoh.operaton.engine.delegate;

import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component("lvDelegate")
public class LVDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RestClient.builder().baseUrl("http://localhost:9080/bkv").build().post().retrieve().toEntity(String.class);
    }
}
