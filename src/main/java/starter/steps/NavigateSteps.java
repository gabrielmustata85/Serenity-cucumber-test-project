package starter.steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.models.Browser;
import starter.models.User;

public class NavigateSteps extends UIInteractionSteps {

    @Steps
    LoginSteps loginSteps;
    @Steps
    User user;
    @Steps
    Browser browser;

    @Step("Navigate to login page")
    public void openTheLoginPage() {
        loginSteps.navigateToLoginPage(user.getUrl());
    }

    @Step("Set up test data")
    public void setUpData() {
        user = new User().getUserTestData(System.getProperty("user"));
        browser = new Browser().getBrowserTestData(System.getProperty("driver"));
    }
}
