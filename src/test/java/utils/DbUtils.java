package utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import utils.config.TestConfiguration;
import utils.model.connection.DbConnectionModel;

public class DbUtils {

    private static final Logger logger = LoggerFactory.getLogger(DbUtils.class);
    private static final Map<String, DbUtils> instancePool = new ConcurrentHashMap<>();
    private final JdbcTemplate jdbc;

    public DbUtils(String driverName, String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        jdbc = new JdbcTemplate(dataSource);
        logger.info("Connection by url: {}", url);
    }

    public static DbUtils getInstance(String benchName) throws IOException {
        DbConnectionModel properties = getProperties(benchName);
        synchronized (DbUtils.class) {
            instancePool.putIfAbsent(benchName, new DbUtils(
                    properties.getDriverName().getValue(),
                    properties.getUrl().getValue(),
                    properties.getUsername().getValue(),
                    properties.getPassword().getValue()
            ));
        }
        return instancePool.get(benchName);
    }

    private static DbConnectionModel getProperties(String benchName) {
        DbConnectionModel postgresConnection = TestConfiguration.getProperties().getPostgresProperties().get(benchName);
        if (postgresConnection != null)
            return postgresConnection;
        else throw new RuntimeException("Not found mssql properties for bench: " +  benchName);
    }

    public Object readValue(String query) {
        return jdbc.queryForObject(query, Object.class);
    }

    public Map<String, Object> readRow(String query) {
        return jdbc.queryForMap(query);
    }

    public List<Map<String, Object>> readRows(String query) {
        return jdbc.queryForList(query);
    }
}