package starter.steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.pages.HomePage;

public class HomeSteps extends UIInteractionSteps {

    @Step
    public void goToAddNewCarPage() {
        find(HomePage.ADD_NEW_CAR_BTN).click();
    }
}
