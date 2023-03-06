package equifax.automation.stepdefinitions;

import equifax.automation.apiclient.PostFedssoEquifaxOauth;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class AuthenticationStepDefinition {

    @Steps
    PostFedssoEquifaxOauth postFedssoEquifaxOauth;
    @Given("the user has the authorization")
    public void theUserHasTheAuthorization() {
        postFedssoEquifaxOauth.postFedssoOauth();
    }
}
