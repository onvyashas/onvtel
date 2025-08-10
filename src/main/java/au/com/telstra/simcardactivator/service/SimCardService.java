package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.dto.ActuatorResponse;
import au.com.telstra.simcardactivator.dto.SimRequest;
import au.com.telstra.simcardactivator.model.SimCardRecord;
import au.com.telstra.simcardactivator.repository.SimCardRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SimCardService {

    private final SimCardRecordRepository repository;
    private final RestTemplate restTemplate;

    public SimCardService(SimCardRecordRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public SimCardRecord activateAndSave(SimRequest request) {
        // call actuator
        Map<String, String> payload = Map.of("iccid", request.getIccid());
        ResponseEntity<ActuatorResponse> resp = restTemplate.postForEntity(
                "http://localhost:8444/actuate", payload, ActuatorResponse.class);

        boolean success = resp.getBody() != null && resp.getBody().isSuccess();

        // save record
        SimCardRecord record = new SimCardRecord(request.getIccid(), request.getCustomerEmail(), success);
        return repository.save(record);
    }

    public SimCardRecord findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
