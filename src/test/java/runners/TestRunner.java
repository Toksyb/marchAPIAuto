package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/feature_files",
        plugin = {"pretty", "html:target/ReportsDestination.html"}, monochrome = true,
        glue = {"step_Definitions"}, tags = "@regression")
public class TestRunner {
}
