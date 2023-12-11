package starter.pages;

import org.openqa.selenium.By;

public class AddNewCarPage extends BasePage {

    public static final By carMakeInput = By.id("makeInput");
    public static final By carModelInput = By.id("modelInput");
    public static final By carYearInput = By.id("yearInput");
    public static final By carEngineCapacityInput = By.id("engineInput");
    public static final By carColorInput = By.id("colorInput");

    public static final By addNewCarBtn = By.id("addNewCar");
    public static final By successMsj = By.xpath("//span[contains(text(),'Your car was added successfully')]");
}
