package stepDefinitions;

import io.cucumber.java.en.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class SimCardActivatorStepDefinitions {

    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<Map> response;

    @Given("I activate a SIM card with ICCID {string} and customer email {string}")
    public void activateSimCard(String iccid, String email) {
        String url = "http://localhost:8080/simcards/activate?iccid=" + iccid + "&customerEmail=" + email;
        response = restTemplate.postForEntity(url, null, Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @When("I query the SIM card record with ID {int}")
    public void querySimCardRecord(Integer id) {
        String url = "http://localhost:8080/simcards?simCardId=" + id;
        response = restTemplate.getForEntity(url, Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Then("the activation status should be {word}")
    public void verifyActivationStatus(String expectedStatus) {
        boolean actual = (boolean) response.getBody().get("active");
        boolean expected = Boolean.parseBoolean(expectedStatus);
        assertEquals(expected, actual);
    }
}
