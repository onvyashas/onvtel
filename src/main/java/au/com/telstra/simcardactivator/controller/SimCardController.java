package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.dto.SimRequest;
import au.com.telstra.simcardactivator.model.SimCardRecord;
import au.com.telstra.simcardactivator.service.SimCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sim")
public class SimCardController {

    private final SimCardService service;

    public SimCardController(SimCardService service) {
        this.service = service;
    }

    @PostMapping("/activate")
    public ResponseEntity<Map<String, Object>> activate(@RequestBody SimRequest request) {
        SimCardRecord saved = service.activateAndSave(request);
        // return only the three required fields for the task
        Map<String, Object> out = Map.of(
                "iccid", saved.getIccid(),
                "customerEmail", saved.getCustomerEmail(),
                "active", saved.isActive()
        );
        return ResponseEntity.ok(out);
    }

    @GetMapping("/record")
    public ResponseEntity<?> getRecord(@RequestParam("simCardId") Long simCardId) {
        SimCardRecord record = service.findById(simCardId);
        if (record == null) return ResponseEntity.notFound().build();
        Map<String, Object> out = Map.of(
                "iccid", record.getIccid(),
                "customerEmail", record.getCustomerEmail(),
                "active", record.isActive()
        );
        return ResponseEntity.ok(out);
    }
}
