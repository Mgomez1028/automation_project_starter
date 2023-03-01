package equifax.automation.stepdefinitions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static equifax.automation.utils.Environment.getSerenityEnvVariable;

public class Hooks {
    protected static final String AP_USA_API_BASE_URL = getSerenityEnvVariable("env.${env}.api.base.url");

    public static RequestSpecification rootAPInternalRequest() {
        return RestAssured.given()
                .baseUri(AP_USA_API_BASE_URL)
                .header("Authorization", "Bearer " + System.getenv("AUTH_TOKEN"))
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .log().all();
    }
}
