package io.mjoh.operaton.engine.controller;

import jakarta.ws.rs.core.Request;
import org.operaton.bpm.engine.RuntimeService;
import org.operaton.bpm.engine.runtime.ProcessInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AnnahmeController {

    private final RuntimeService runtimeService;

    public AnnahmeController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("application")
    public ResponseEntity<String> application(@RequestBody String payload) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("VersicherungsMapper", Map.of("payload", payload));
        return ResponseEntity.ok(processInstance.getId());
    }
}
