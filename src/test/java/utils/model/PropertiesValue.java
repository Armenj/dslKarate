package utils.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PropertiesValue {
    private String value;
    private String envName;

    public String getValue() {
        if (System.getenv(envName) != null && envName != null) {
            return System.getenv(envName);
        } else {
            return this.value;
        }
    }
}