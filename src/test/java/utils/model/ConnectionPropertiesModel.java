package utils.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import utils.model.connection.DbConnectionModel;
import java.util.Map;


@Data
@RequiredArgsConstructor
public class ConnectionPropertiesModel {

    private Map<String, DbConnectionModel> oracleProperties;

    private Map<String, DbConnectionModel> mssqlProperties;

    private Map<String, DbConnectionModel> postgresProperties;
}