package co.com.sofka.runner.users;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        plugin = {"pretty", "html:target/cucumber-report/cucumber.html"},
        features = {"src/test/resources/features/webui/users/user.feature"},
        glue = {"co.com.sofka.stepdefinition.users"},
        tags = "not @ignore"
)

public class UsersCucumberTest {
}
