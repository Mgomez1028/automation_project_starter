package equifax.automation.apiclient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static equifax.automation.enums.DomainConstants.DO_CONSUMER_IDENTITY_DOMAIN;
import static equifax.automation.enums.SubdomainConstants.TABLE_NAME_JCE_PURP;

public class LookUpJCEPurposingAPI {

    @Step("the lookup purposing is called with entityKey {}")
    public void queryPurposingServiceByEntityKey(String entityKey) {
        RestAssured.baseURI = "https://api.la-int.df.nane1.gcp.efx";
        RestAssured.urlEncodingEnabled = false;
        String JCEJournalingPath = String.format("prp-int-lat/authoring-api-purposing/purposing/lookup/v2/%s/%s?entityKey=%s&format=json", DO_CONSUMER_IDENTITY_DOMAIN.toString(), TABLE_NAME_JCE_PURP.toString(), entityKey);

        SerenityRest.given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + System.getenv("AUTH_TOKEN")).log().all()
                .get(JCEJournalingPath);
    }
}
