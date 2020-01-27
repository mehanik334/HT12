package ua.epam.repositories.jdbc;

import ua.epam.model.Developer;
import ua.epam.model.Skill;
import ua.epam.repositories.DeveloperRepository;
import ua.epam.util.ConnectionPoolDB;
import ua.epam.util.UtilsJDBC;
import ua.epam.util.mappers.ModelMapperJDBC;

import java.sql.*;
import java.util.ArrayList;

public class DeveloperJDBCRepositoryImpl implements DeveloperRepository {
    @Override
    public Developer getById(Long aLong) {
        String sql = "SELECT d.id, d.firstName, d.lastName, a.id, a.status, s.id, s.name\n" +
                "FROM developers d\n" +
                "         JOIN developer_skills ds ON d.id = ds.developers_id\n" +
                "         JOIN skills s ON ds.skills_id = s.id\n" +
                "         JOIN accounts a ON d.accounts_id = a.id \n" +
                "WHERE d.id = " + aLong + ";";
        return developerGetFromDB(sql).get(0);
    }

    @Override
    public ArrayList<Developer> getAll() {
        String sql = "SELECT d.id, d.firstName, d.lastName, a.id, a.status, s.id, s.name\n" +
                "FROM developers d\n" +
                "         JOIN developer_skills ds ON d.id = ds.developers_id\n" +
                "         JOIN skills s ON ds.skills_id = s.id\n" +
                "         JOIN accounts a ON d.accounts_id = a.id;";
        return developerGetFromDB(sql);
    }

    @Override
    public Developer save(Developer obj) {
        String sql1 = "INSERT INTO developers(firstName, lastName, accounts_id) VALUES (?, ?, ?);";
        String sql2 = "INSERT INTO developer_skills(developers_id, skills_id) VALUES (?, ?);";
        String sql3 = "SELECT * FROM developers ;";
        developerWriteToDB(sql1, sql2, sql3, obj);
        ArrayList<Developer> developers = getAll();
        return developers.get(developers.size() - 1);
    }


    @Override
    public Developer deleteById(Long aLong) {
        Developer developer = getById(aLong);
        String sql1 = "DELETE FROM developers WHERE id = ?;";
        String sql2 = "DELETE FROM developer_skills WHERE developers_id = ?;";
        UtilsJDBC.deleteFromDB(sql2,aLong);
        UtilsJDBC.deleteFromDB(sql1,aLong);

        return developer;
    }

    @Override
    public boolean update(Developer obj) {
        return false;
    }

    private ArrayList<Developer> developerGetFromDB(String sql) {
        ArrayList<Developer> developers = null;
        try (Connection connection = ConnectionPoolDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            developers = ModelMapperJDBC.mapToDeveloper(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    private void developerWriteToDB(String sql1, String sql2, String sql3, Developer obj) {
        ArrayList<Developer> developers = null;
        try (Connection connection = ConnectionPoolDB.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setString(1, obj.getFirstName());
                preparedStatement.setString(2, obj.getLastName());
                preparedStatement.setLong(3, obj.getAccount().getId());
                preparedStatement.executeUpdate();
            }
            developers = developersEmptySkillGetFromDB(sql3);
            Long idDev = developers.get(developers.size() - 1).getId();
            ArrayList<Skill> skills = obj.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                    preparedStatement.setLong(1, idDev);
                    preparedStatement.setLong(2, skills.get(i).getId());
                    preparedStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Developer> developersEmptySkillGetFromDB(String sql) {
        ArrayList<Developer> developers = new ArrayList<>();
        try (Connection connection = ConnectionPoolDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long developerId = resultSet.getInt("id");

                String firstNameDeveloper = resultSet.getString("firstName");
                String lastNameDeveloper = resultSet.getString("lastName");

                Developer dev = new Developer();

                dev.setId(developerId);
                dev.setFirstName(firstNameDeveloper);
                dev.setLastName(lastNameDeveloper);
                developers.add(dev);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }
}
