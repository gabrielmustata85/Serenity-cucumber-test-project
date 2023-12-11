package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import starter.models.CarDetails;
import starter.models.User;
import starter.steps.AddNewCarSteps;
import starter.steps.HomeSteps;
import starter.steps.LoginSteps;
import starter.steps.NavigateSteps;
import starter.util.CucumberUtil;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AddNewCarStepDefinitions {

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

    @Given("User is logged in")
    public void user_is_logged_in() {
        navigateSteps.setUpData();
        navigateSteps.openTheLoginPage();
        loginSteps.loginIntoTheApp(user.getUsername(), user.getPassword());
    }

    @When("User navigates to the add new car page")
    public void user_navigates_to_the_add_new_car_page() {
        homeSteps.goToAddNewCarPage();
    }

    @Then("The new care page is visible")
    public void the_new_care_page_is_visible() {
        assertThat(addNewCarSteps.verifyThePageUrl().containsText("/addNewCar"));
    }

    @Then("User enters car details")
    public void userEntersCarDetails(DataTable dataTable) {
        List<CarDetails> carDetailsList = new ArrayList<>();

        for (Map<String, String> entry : dataTable.asMaps()) {
            CarDetails carDetails = cucumberUtil.convert(entry);
            carDetailsList.add(carDetails);
        }
        addNewCarSteps.addCarDetails(carDetailsList);
    }


    @When("User click on add new car button")
    public void user_click_on_add_new_car_button() {
        addNewCarSteps.clickOnAddNewCar();
    }

    @Then("Success message will be visible")
    public void success_message_will_be_visible() {
        addNewCarSteps.verifyIfSuccessMsjIsVisible();
    }
}
