package ua.epam.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolDB {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl(PropertiesDB.getProperties().get(0));
        dataSource.setUsername(PropertiesDB.getProperties().get(1));
        dataSource.setPassword(PropertiesDB.getProperties().get(2));
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionPoolDB() {
    }
}
