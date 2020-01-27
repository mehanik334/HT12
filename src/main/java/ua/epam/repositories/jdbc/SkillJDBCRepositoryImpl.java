package ua.epam.repositories.jdbc;

import ua.epam.model.Skill;
import ua.epam.repositories.SkillRepository;
import ua.epam.util.ConnectionPoolDB;
import ua.epam.util.UtilsJDBC;
import ua.epam.util.mappers.ModelMapperJDBC;

import java.sql.*;
import java.util.ArrayList;

public class SkillJDBCRepositoryImpl implements SkillRepository {
    @Override
    public Skill getById(Long aLong) {
        String sql = "SELECT name,id FROM skills WHERE skills.id = " + aLong + ";";
        return skillsGetFromDB(sql).get(0);
    }

    @Override
    public ArrayList<Skill> getAll() {
        String sql = "SELECT * FROM skills";
        return skillsGetFromDB(sql);
    }

    @Override
    public Skill save(Skill obj) {
        String sql = "INSERT INTO skills(name) VALUES (?)";
        String sql1 = "SELECT * FROM skills WHERE name = ?;";
        return insertSkillToDB(sql, sql1, obj);
    }

    @Override
    public Skill deleteById(Long aLong) {
        Skill skill = getById(aLong);
        String sql = "DELETE FROM skills WHERE id = " + aLong + ";";
        UtilsJDBC.deleteFromDB(sql, aLong);
        return skill;
    }

    @Override
    public boolean update(Skill obj) {
        String sql = "UPDATE skills SET name = ? WHERE id = ?;";
        return updateSkillInDB(sql, obj);
    }

    private ArrayList<Skill> skillsGetFromDB(String sql) {
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            Connection connection = ConnectionPoolDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            skills = ModelMapperJDBC.getSkillListFromMap(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    private Skill insertSkillToDB(String sql1, String sql2, Skill skill) {
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            Connection connection = ConnectionPoolDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setString(1, skill.getName());
            ps.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement(sql2);
            ps1.setString(1, skill.getName());
            ResultSet resultSet = ps1.executeQuery();
            skills = ModelMapperJDBC.getSkillListFromMap(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills.get(0);
    }

    private boolean updateSkillInDB(String sql, Skill skill) {
        boolean flag = false;
        try {
            Connection connection = ConnectionPoolDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, skill.getName());
            ps.setLong(2, skill.getId());
            flag = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
