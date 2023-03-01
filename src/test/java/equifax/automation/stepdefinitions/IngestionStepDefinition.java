package equifax.automation.stepdefinitions;

import equifax.automation.apiclient.DataFabricLineageAPI;
import io.cucumber.java.en.And;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.Ensure;
import net.thucydides.core.annotations.Steps;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class IngestionStepDefinition {

    @Steps
    DataFabricLineageAPI apServices;

    @And("the ingestion process run in BATCH mode")
    public void theIngestionProcessRunInBATCHMode() {
        apServices.searchesForSourcesByCorrelationIdInDomino(Serenity.sessionVariableCalled("correlationId").toString());
        //apServices.searchesForSourcesByCorrelationIdInDomino("5b5d6173-3988-415d-a2af-25c0e92dd9dc");
        Ensure.that("The ingestion process was executed successfully.",
                response -> response.statusCode(SC_OK)
        );
    }
}
