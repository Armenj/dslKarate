package utils.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import utils.model.ConnectionPropertiesModel;

import java.io.File;
import java.io.IOException;


public class TestConfiguration {
    private static final String PATH_TO_FILE_PROPERTIES = "src/test/resources/application.yaml";

    private static final ConnectionPropertiesModel properties;

    static {
        try {
            properties = new ObjectMapper(new YAMLFactory()).readValue(
                    new File(PATH_TO_FILE_PROPERTIES),
                    ConnectionPropertiesModel.class
            );
        } catch (IOException e) {
            throw new RuntimeException("Error parse application properties: ", e.getCause());
        }
    }

    public static ConnectionPropertiesModel getProperties() {
        return properties;
    }
}