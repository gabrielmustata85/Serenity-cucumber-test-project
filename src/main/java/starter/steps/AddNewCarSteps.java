package starter.steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.models.CarDetails;
import starter.pages.AddNewCarPage;

import java.util.*;

public class AddNewCarSteps extends UIInteractionSteps {

    @Step
    public AddNewCarSteps verifyThePageUrl() {
        getDriver().getCurrentUrl();
        return this;
    }

    @Step
    public void addCarDetails(List<CarDetails> carDetailsList) {
        for (CarDetails carDetails : carDetailsList) {
            find(AddNewCarPage.carMakeInput).type(carDetails.getMake());
            find(AddNewCarPage.carModelInput).type(carDetails.getModel());
            find(AddNewCarPage.carYearInput).type(carDetails.getYear());
            find(AddNewCarPage.carEngineCapacityInput).type(carDetails.getEngineSize());
            find(AddNewCarPage.carColorInput).type(carDetails.getColor());
        }
    }

    @Step
    public void clickOnAddNewCar() {
        find(AddNewCarPage.addNewCarBtn).click();
    }

    public void verifyIfSuccessMsjIsVisible() {
        find(AddNewCarPage.successMsj).isVisible();
    }
}
