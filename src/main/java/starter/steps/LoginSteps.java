package starter.steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.pages.HomePage;
import starter.pages.LoginPage;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginSteps extends UIInteractionSteps {

    @Step("Login process")
    public void loginIntoTheApp(String email, String password) {
        find(LoginPage.EMAIL_INPUT).type(email);
        find(LoginPage.PASSWORD_INPUT).type(password);
        find(LoginPage.LOGIN_BTN).click();
        withTimeoutOf(Duration.ofSeconds(10))
                .waitFor(presenceOfElementLocated(HomePage.HOMEPAGE_LOGO));
    }

    @Step("Navigate to Login page")
    public void navigateToLoginPage(String url) {
        getDriver().navigate().to(url);
    }

}
