package equifax.automation.apiclient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class LookUpIntJournalingAPI {

    @Step("the lookup dataRiskEvaluation is called with matchKey {}")
    public void queryJournalingServiceByMatchkey(String matchKey) {
        RestAssured.baseURI = "https://api.int.df.use1.gcp.efx";
        String interactionsJournalingPath = String.format("prp-int/authoring-api-journaling/journaling/v3/lookup/INTERACTIONS/INTERACTIONS/PAYMENT_RISK_EVALUATION?requestId=1234&matchKey=%s&format=json", matchKey);

        SerenityRest.given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + System.getenv("AUTH_TOKEN")).log().all()
                .get(interactionsJournalingPath);
    }
}
