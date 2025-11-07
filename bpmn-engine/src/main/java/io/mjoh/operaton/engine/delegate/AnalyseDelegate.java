package io.mjoh.operaton.engine.delegate;

import org.operaton.bpm.engine.RuntimeService;
import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.operaton.spin.Spin;
import org.operaton.spin.json.SpinJsonNode;
import org.springframework.stereotype.Component;

import static org.operaton.spin.DataFormats.json;

@Component("analyseDelegate")
public class AnalyseDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String reqPayload = (String) delegateExecution.getVariable("payload");

        SpinJsonNode spinJsonNode = Spin.S(reqPayload, json());
        String id = spinJsonNode.prop("id").stringValue();
        String consumer = spinJsonNode.prop("consumer").stringValue();
        String payload = spinJsonNode.prop("payload").stringValue();

        SpinJsonNode spinJsonNode1 = Spin.S(payload, json());
        String sparte = spinJsonNode1.prop("sparte").stringValue();
        String payload1 = spinJsonNode1.prop("payload").stringValue();

        delegateExecution.setVariable("id", id);
        delegateExecution.setVariable("consumer", consumer);
        delegateExecution.setVariable("sparte", sparte);
        delegateExecution.setVariable("payload1", payload1);
    }
}
