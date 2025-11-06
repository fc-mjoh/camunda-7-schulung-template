package io.mjoh.operaton.engine.delegate;

import org.operaton.bpm.engine.RuntimeService;
import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class NotifyDelegate implements JavaDelegate {
    private final RuntimeService runtimeService;

    public NotifyDelegate(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        runtimeService.startProcessInstanceByMessage("StartEvent_1");
    }
}
