package starter.util;

import io.cucumber.java.DataTableType;
import starter.models.CarDetails;

import java.util.Map;

public class CucumberUtil {

    @DataTableType
    public CarDetails convert(Map<String, String> entry) {
        CarDetails carDetails = new CarDetails();
        carDetails.setMake(entry.get("make"));
        carDetails.setModel(entry.get("model"));
        carDetails.setYear(entry.get("year"));
        carDetails.setColor(entry.get("color"));
        carDetails.setEngineSize(entry.get("engineSize"));
        return carDetails;
    }
}

