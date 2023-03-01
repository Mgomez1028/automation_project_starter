package equifax.automation.jpvalidations;

import com.google.gson.JsonObject;
import net.thucydides.core.annotations.Step;


import java.util.HashMap;

import org.assertj.core.api.SoftAssertions;

public class ValidationData {

    @Step("Validating json response vs expected result")
    public void validateResponse(HashMap<String, String> expectedData, JsonObject actualJsonResponse) {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualJsonResponse.get("affiliateIdNumber").getAsString())
                .as("affiliateIdNumber doesn't match. it should be %s", expectedData.get("affiliateIdNumber"))
                .isEqualTo(expectedData.get("affiliateIdNumber"));

        softAssertions.assertThat(actualJsonResponse.get("batchInitialRecordCode").getAsString())
                .as("batchInitialRecordCode doesn't match. it should be %s", expectedData.get("batchInitialRecordCode"))
                .isEqualTo(expectedData.get("batchInitialRecordCode"));

        softAssertions.assertThat(actualJsonResponse.get("personIdNumber1").getAsString())
                .as("personIdNumber1 doesn't match. it should be %s", expectedData.get("personIdNumber1"))
                .isEqualTo(expectedData.get("personIdNumber1"));

        softAssertions.assertThat(actualJsonResponse.get("idCardExpirationDate").getAsString())
                .as("idCardExpirationDate doesn't match. it should be %s", expectedData.get("idCardExpirationDate"))
                .isEqualTo(expectedData.get("idCardExpirationDate"));


        softAssertions.assertThat(actualJsonResponse.get("batchSeqNumber").getAsString())
                .as("batchSeqNumber doesn't match. it should be %s", expectedData.get("batchSeqNumber"))
                .isEqualTo(expectedData.get("batchSeqNumber"));

        softAssertions.assertThat(actualJsonResponse.get("firstName").getAsString())
                .as("firstName doesn't match. it should be %s", expectedData.get("firstName"))
                .isEqualTo(expectedData.get("firstName"));
        softAssertions.assertAll();
    }
}
