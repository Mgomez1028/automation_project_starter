package equifax.automation;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:features",
        //features = "src/test/resources/features/datapipelines",
        glue = {"equifax/automation/stepdefinitions"}
)

public class CucumberTestSuite {}
