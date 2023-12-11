package starter.steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.pages.CarListingPage;

public class CarListingSteps extends UIInteractionSteps {

    @Step
    public void typeCarName(String name) {
        find(CarListingPage.SEARCH_INPUT).type(name);
    }
}
