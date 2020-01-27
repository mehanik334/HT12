package ua.epam.repositories.jdbc;

import ua.epam.model.Account;
import ua.epam.repositories.AccountRepository;
import ua.epam.util.ConnectionPoolDB;
import ua.epam.util.mappers.ModelMapperJDBC;
import ua.epam.util.UtilsJDBC;

import java.sql.*;
import java.util.ArrayList;

public class AccountJDBCRepositoryImpl implements AccountRepository {
    @Override
    public Account getById(Long aLong) {
        String sql = "SELECT status,id FROM accounts WHERE accounts.id = " + aLong + ";";
        return accountsGetFromDB(sql).get(0);
    }

    @Override
    public ArrayList<Account> getAll() {
        String sql = "SELECT * FROM accounts";
        return accountsGetFromDB(sql);
    }

    @Override
    public Account save(Account obj) {
        String sql = "INSERT INTO accounts(status) VALUES (?)";
        String sql1 = "SELECT * FROM accounts WHERE status = ?;";
        return insertAccountToDB(sql, sql1, obj);
    }

    @Override
    public Account deleteById(Long aLong) {
        Account account = getById(aLong);
        String sql = "DELETE FROM accounts WHERE id = " + aLong + ";";
        UtilsJDBC.deleteFromDB(sql, aLong);
        return account;
    }

    @Override
    public boolean update(Account obj) {
        String sql = "UPDATE accounts SET status = ? WHERE id = ?;";
        return updateAccountInDB(sql, obj);
    }

    private Account insertAccountToDB(String sql1, String sql2, Account account) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Connection connection = ConnectionPoolDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setString(1, account.getAccountStatus().toString());
            ps.executeUpdate();
            PreparedStatement ps1 = connection.prepareStatement(sql2);
            ps1.setString(1, account.getAccountStatus().toString());
            ResultSet resultSet = ps1.executeQuery();
            accounts = ModelMapperJDBC.getAccountListFromMap(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts.get(0);
    }

    private ArrayList<Account> accountsGetFromDB(String sql) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Account account = new Account();
            Connection connection = ConnectionPoolDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            accounts = ModelMapperJDBC.getAccountListFromMap(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    private boolean updateAccountInDB(String sql, Account account) {
        boolean flag = false;
        try {
            Connection connection = ConnectionPoolDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getAccountStatus().toString());
            ps.setLong(2, account.getId());
            flag = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
