package equifax.automation.stepdefinitions;

import com.google.gson.JsonObject;
import equifax.automation.apiclient.LookUpIntJournalingAPI;
import equifax.automation.apiclient.LookUpJCEJournalingAPI;
import equifax.automation.apiclient.LookUpJCEPurposingAPI;
import equifax.automation.apiclient.RunPurposingJobAPI;
import equifax.automation.jpvalidations.GetDataToJson;
import equifax.automation.jpvalidations.GettingDataFromFiles;
import equifax.automation.jpvalidations.ValidationData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;

import java.util.HashMap;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;


public class ValidateInformationStepDefinition {

    @Steps
    GettingDataFromFiles gettingDataFromFiles;

    @Steps
    LookUpJCEJournalingAPI lookUpJCEJournalingAPI;

    @Steps
    LookUpJCEPurposingAPI lookUpJCEPurposingAPI;

    @Steps
    LookUpIntJournalingAPI lookUpINTERACTIONSJournalingAPI;

    @Steps
    RunPurposingJobAPI runPurposingJobAPI;

    @Steps
    ValidationData validationData;

    @Given("the JOURNALING services run successfully")
    public void theJOURNALINGServicesRunSuccessfully() {
        lookUpJCEJournalingAPI.queryJournalingServiceByMatchkey("96UOxEB5lnqCGZ3hmEukqDhewnQhk0FIsJ9u3viTlb7J0lH9F%2BMmzM08VQ%2BkSikcOeIfkDTVhqqh6e4Um4GoQQ%3D%3D");
        assertThat("Failed Jounaling services: " + SerenityRest.lastResponse().getBody().prettyPrint(), SerenityRest.lastResponse().getStatusCode(), Matchers.equalTo(SC_OK));
    }

    @Given("the PURPOSING services run successfully")
    public void thePURPOSINGServicesRunSuccessfully() {
        lookUpJCEPurposingAPI.queryPurposingServiceByEntityKey("96UOxEB5lnqCGZ3hmEukqDhewnQhk0FIsJ9u3viTlb7J0lH9F%2BMmzM08VQ%2BkSikcOeIfkDTVhqqh6e4Um4GoQQ%3D%3D");
        assertThat("Failed Purposing services: " + SerenityRest.lastResponse().getBody().prettyPrint(), SerenityRest.lastResponse().getStatusCode(), Matchers.equalTo(SC_OK));
    }

    @Given("that the data has been correctly stored")
    public void thatTheDataHasBeenCorrectlyStored() {
        JsonObject actualJsonResponse = GetDataToJson.getDataAsObject();
        HashMap<String, String> expectedData = gettingDataFromFiles.gettingExpectedData();
        validationData.validateResponse(expectedData,actualJsonResponse);
    }

    @When("the PURPOSING JOB IN RUN")
    public void thePURPOSINGJOBINRUN() {
        runPurposingJobAPI.runPurposingJob();
        assertThat("Failed run purposing job: " + SerenityRest.lastResponse().getBody().prettyPrint(), SerenityRest.lastResponse().getStatusCode(), Matchers.equalTo(SC_OK));
    }
}
