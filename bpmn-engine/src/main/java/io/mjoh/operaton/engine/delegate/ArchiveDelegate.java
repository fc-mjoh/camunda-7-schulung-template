package io.mjoh.operaton.engine.delegate;

import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component("archiveDelegate")
public class ArchiveDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RestClient.builder().baseUrl("http://localhost:9080/dok-api").build().post().retrieve().toEntity(String.class).getBody();
    }
}
