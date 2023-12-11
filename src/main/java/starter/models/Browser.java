package starter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.util.JsonUtils;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Browser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Browser.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List args;
    private String browserName;
    private boolean webdriverAcceptUntrustedCerts;
    private boolean webdriverAssumeUntrustedIssuer;

    public List getArgs() {
        return args;
    }

    public String getBrowserName() {
        return browserName;
    }

    public boolean isWebdriverAcceptUntrustedCerts() {
        return webdriverAcceptUntrustedCerts;
    }

    public boolean isWebdriverAssumeUntrustedIssuer() {
        return webdriverAssumeUntrustedIssuer;
    }

    @SneakyThrows
    public Browser getBrowserTestData(String browserDataToUse) {
        LOGGER.info("Browser name to search is: " + browserDataToUse);
        JSONObject jsonObject = JsonUtils.getBrowserData(browserDataToUse);

        if (jsonObject == null) {
            LOGGER.warn("The browserData.json is missing, the browser will start with default options");
        } else {
            try {
                return objectMapper.readValue(jsonObject.toJSONString(), Browser.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return null;
    }
}
