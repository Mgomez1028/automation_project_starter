package equifax.automation.apiclient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


import static equifax.automation.enums.DomainConstants.DO_CONSUMER_IDENTITY_DOMAIN;
import static equifax.automation.enums.SubdomainConstants.JCE_REGISTRATION_SUBDOMAIN;
import static equifax.automation.enums.SubdomainConstants.TABLE_NAME_JCE_JOUR;

public class LookUpJCEJournalingAPI {

    @Step("the lookup journaling is called with #matchKey {0}")
    public void queryJournalingServiceByMatchkey(String matchKey) {
        RestAssured.baseURI = "https://api.la-int.df.nane1.gcp.efx";
        RestAssured.urlEncodingEnabled = false;
        String JCEJournalingPath = String.format("prp-int-lat/authoring-api-journaling/journaling/v3/lookup/%s/%s/%s?matchKey=%s&format=json", TABLE_NAME_JCE_JOUR.toString(), DO_CONSUMER_IDENTITY_DOMAIN.toString(), JCE_REGISTRATION_SUBDOMAIN.toString(), matchKey);

        SerenityRest.given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + System.getenv("AUTH_TOKEN")).log().all()
                .get(JCEJournalingPath);

        Serenity.setSessionVariable("journalresponsedata").to(SerenityRest.lastResponse().getBody().jsonPath().get("data"));
    }
}
