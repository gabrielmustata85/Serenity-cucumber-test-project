package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import starter.models.User;
import starter.steps.*;
import starter.util.CucumberUtil;

import java.util.List;

import static java.util.logging.Logger.getLogger;

public class SearchCarStepDefinitions {

    @Steps
    LoginSteps loginSteps;

    @Steps
    HomeSteps homeSteps;
    @Steps
    NavigateSteps navigateSteps;
    @Steps
    AddNewCarSteps addNewCarSteps;
    @Steps
    CucumberUtil cucumberUtil;
    @Steps
    User user;
    @Steps
    CarListingSteps carListingSteps;

    @Given("User is logged in")
    public void userIsLoggedIn() {
        navigateSteps.setUpData();
        navigateSteps.openTheLoginPage();
        loginSteps.loginIntoTheApp(user.getUsername(), user.getPassword());
    }

    @And("User goes to the car listing page")
    public void userGoesToCarListingPage() {
        homeSteps.goToCarListingPage();
    }

    @And("User types in the search bar a car name")
    public void userSearchesForCars(DataTable carNames) {
        List<String> carNameList = carNames.asList(String.class);
        for (String carName : carNameList) {
            carListingSteps.typeCarName(carName);
        }
    }

    @When("User press the search button")
    public void userPressesSearchButton() {
        // Implementation for pressing the search button
    }

    @Then("The results should be visible")
    public void resultsShouldBeVisible() {
        // Implementation for verifying that the results are visible
    }

    @And("User click on the filter button")
    public void userClicksOnFilterButton() {
        // Implementation for clicking on the filter button
    }

    @And("User selects a year range from {int} to {int}")
    public void userSelectsYearRange(int from, int to) {
        // Implementation for selecting a year range
    }

    @And("User selects a mileage range from {double} to {double}")
    public void userSelectsMileageRange(double from, double to) {
        // Implementation for selecting a mileage range
    }
}
