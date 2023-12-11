package starter.util;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.config.DriverManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static final String DEFAULT_TEST_DATA_PATH = "src/main/resources/testData.json";
    private static final String DEFAULT_BROWSER_TEST_DATA_PATH = "src/main/resources/browserData.json";
    private static final String TEST_DATA_JSON = System.getProperty("testData");
    private static final String BROWSER_DATA_JSON = System.getProperty("browserData");
    private static final String TEST_DATA_JSON_PATH = getTestDataJsonPath();
    private static final String BROWSER_DATA_JSON_PATH = getBrowserDataJsonPath();

    public static JSONObject getTestData(String testDataName) {
        return getTestData(testDataName, TEST_DATA_JSON_PATH);
    }

    public static JSONObject getTestData(String testDataName, String jsonFilePath) {
        LOGGER.info("Get test data from key {} using json file with path: {}", testDataName, jsonFilePath);
        File configFile = FileUtils.getFile(jsonFilePath);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(configFile.getAbsoluteFile()));
            return (JSONObject) jsonObject.get(testDataName);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getTestDataJsonPath() {
        if (TEST_DATA_JSON != null) {
            LOGGER.info("TEST DATA IS SET FROM MVN COMMAND!");
            return "src/test/resources/" + TEST_DATA_JSON + ".json";
        } else {
            LOGGER.info("The testData environment value is not set, it's using the default path: " + DEFAULT_TEST_DATA_PATH);
            return DEFAULT_TEST_DATA_PATH;
        }
    }

    public static JSONObject getBrowserData(String browserName) {
        File browserConfigFile = FileUtils.getFile(BROWSER_DATA_JSON_PATH);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(browserConfigFile.getAbsoluteFile()));
            return (JSONObject) jsonObject.get(browserName);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getBrowserDataJsonPath() {
        if (BROWSER_DATA_JSON != null) {
            LOGGER.info("BROWSER DATA IS SET FROM MVN COMMAND!");
            return "src/main/resources/" + BROWSER_DATA_JSON + ".json";
        } else {
            LOGGER.info("The browserData environment value is not set, it's using the default path: " + DEFAULT_BROWSER_TEST_DATA_PATH);
            return DEFAULT_BROWSER_TEST_DATA_PATH;
        }
    }
}