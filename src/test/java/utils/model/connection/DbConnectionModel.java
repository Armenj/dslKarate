package utils.model.connection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import utils.model.PropertiesValue;

@Getter
@RequiredArgsConstructor
public class DbConnectionModel {

    private PropertiesValue url;

    private PropertiesValue driverName;

    private PropertiesValue username;

    private PropertiesValue password;
}