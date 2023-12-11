package starter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.util.JsonUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String username;
    private String password;
    private String url;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    @SneakyThrows
    public User getUserTestData(String testDataToUse) {
        LOGGER.info("Tests will run with user: " + testDataToUse);
        User json = null;

        try {
            JSONObject jsonObject = JsonUtils.getTestData(testDataToUse);

            if (jsonObject == null) {
                LOGGER.warn("Use data for user associated to {}", testDataToUse);
                Assert.fail("The JSON object is null, check the: " + testDataToUse + " path to not be an invalid path or null");
            }
            json = objectMapper.readValue(jsonObject.toJSONString(), User.class);

        } catch (Exception ignored) {
        }
        return json;
    }
}
