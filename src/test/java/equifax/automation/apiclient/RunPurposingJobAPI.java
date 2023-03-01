package equifax.automation.apiclient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static equifax.automation.enums.SubdomainConstants.TABLE_NAME_JCE_PURP;

public class RunPurposingJobAPI {
    @Step("the Purposing Job is run}")
    public void runPurposingJob() {
        RestAssured.baseURI = "https://api.la-int.df.nane1.gcp.efx";
        RestAssured.urlEncodingEnabled = false;
        String JCERunJobPath = String.format("prp-int-lat/authoring-api-operation/purposing/job/run/%s", TABLE_NAME_JCE_PURP.toString());

        SerenityRest.given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + System.getenv("AUTH_TOKEN")).log().all()
                .post(JCERunJobPath);
    }
}
