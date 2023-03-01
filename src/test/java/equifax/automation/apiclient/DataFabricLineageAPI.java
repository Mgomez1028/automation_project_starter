package equifax.automation.apiclient;

import equifax.automation.enums.DomainConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static equifax.automation.stepdefinitions.Hooks.rootAPInternalRequest;
import static equifax.automation.utils.SetTime.setEndOfDay;
import static equifax.automation.utils.SetTime.setStartOfDay;

public class DataFabricLineageAPI {
    @SneakyThrows
    @Step("#dfCorrelationId {0} is generated to find the monitoring jobs")
    public void searchesForSourcesByCorrelationId(String correlationId) {
        String dfCorrelationId = "5b5d6173-3988-415d-a2af-25c0e92dd9dc";
        Thread.sleep(10_000L);
        String domainName = DomainConstants.INTERACTIONS_DOMAIN.toString();
        SerenityRest.given()
                .spec(rootAPInternalRequest())
                .pathParams("domainName", domainName, "dfCorrelationId", dfCorrelationId, "startDate", setStartOfDay(), "endDate", setEndOfDay())
                .get("intel-int/dint-opi-ui-job-management/api/job-management-service/lineage?domain={domainName}&dfCorrelationId={dfCorrelationId}&startDate={startDate}&endDate={endDate}&isBatchPurposing=false");
    }

    @SneakyThrows
    @Step("#dfCorrelationId {0} is generated to find Data Fabric information")
    public void searchesForSourcesByCorrelationIdInDomino(String correlationId) {
        RestAssured.baseURI = "https://api.la-int.df.nane1.gcp.efx";
        Thread.sleep(10_000L);
        String domainName = DomainConstants.DO_CONSUMER_IDENTITY_DOMAIN.toString();
        SerenityRest.given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + System.getenv("AUTH_TOKEN")).log().all()
                .pathParams("domainName", domainName, "dfCorrelationId", correlationId, "startDate", setStartOfDay(), "endDate", setEndOfDay())
                .get("intel-int-lat/dint-opi-ui-job-management/api/job-management-service/lineage?domain={domainName}&dfCorrelationId={dfCorrelationId}&startDate={startDate}&endDate={endDate}&isBatchPurposing=false");
    }
}
