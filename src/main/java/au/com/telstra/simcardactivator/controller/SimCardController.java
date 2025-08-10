package au.com.telstra.simcardactivator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sim")
public class SimCardController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody Map<String, String> request) {
        String iccid = request.get("iccid");

        // Create request body for actuator
        Map<String, String> actuatorRequest = new HashMap<>();
        actuatorRequest.put("iccid", iccid);

        // Send POST to actuator
        ResponseEntity<Map> actuatorResponse = restTemplate.postForEntity(
                "http://localhost:8444/actuate",
                actuatorRequest,
                Map.class
        );

        boolean success = (boolean) actuatorResponse.getBody().get("success");

        return ResponseEntity.ok(success ? "Activation successful" : "Activation failed");
    }
}
