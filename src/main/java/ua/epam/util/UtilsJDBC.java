package ua.epam.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilsJDBC {

    public static void deleteFromDB(String sql, Long id) {
        try (Connection connection = ConnectionPoolDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setLong(1, id);
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
