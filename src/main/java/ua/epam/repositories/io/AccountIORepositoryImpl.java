package ua.epam.repositories.io;


import ua.epam.model.Account;
import ua.epam.model.AccountStatus;
import ua.epam.repositories.AccountRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AccountIORepositoryImpl implements AccountRepository {

    public static final String FILE_PATH = "account.txt";
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;

    public AccountIORepositoryImpl() {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
            this.fileWriter = new FileWriter(FILE_PATH, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Account getById(Long id) {
        try {
            String accountStr = bufferedReader.readLine();
            while (accountStr != null) {
                Account account = transformStringToAccount(accountStr);
                if (id.equals(account.getId())) {
                    return account;
                }
                accountStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Account> getAll() {
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            String accountStr = bufferedReader.readLine();
            while (accountStr != null) {
                accountList.add(transformStringToAccount(accountStr));
                accountStr = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public Account save(Account account) {
        if (!hasAccount(account)) {
            try {
                fileWriter.append('\n');
                fileWriter.write(account.toString());

                fileWriter.flush();
                return account;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return account;
    }

    @Override
    public Account deleteById(Long id) {
        try {
            String accountStr = bufferedReader.readLine();
            ArrayList<Account> accountList = new ArrayList<>();
            Account account = null;
            while (accountStr != null) {
                account = transformStringToAccount(accountStr);
                accountList.add(account);
                if (id.equals(account.getId())) {
                    accountList.remove(account);
                }
                accountStr = bufferedReader.readLine();
            }
            saveAccountListToFile(accountList);
            return account;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Account account) {
        try {
            String accountStr = bufferedReader.readLine();
            while (accountStr != null) {
                Account accountFromFile = transformStringToAccount(accountStr);
                if (account.getId().equals(accountFromFile.getId())) {
                    fileWriter.write(account.toString());
                    fileWriter.append('\n');
                    fileWriter.flush();
                    return true;
                }
                accountStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Account transformStringToAccount(String accountStr) {
        String[] arrStr = accountStr.split("-");
        return new Account(Long.parseLong(arrStr[0]), AccountStatus.valueOf(arrStr[1]));
    }

    private void saveAccountListToFile(ArrayList<Account> accountList) {
        for (Account account : accountList) {
            save(account);
        }
    }

    public boolean hasAccount(Account account) {
        ArrayList<Account> allAccount = getAll();
        for (Account a : allAccount) {
            if (a.equals(account)) {
                return true;
            }
        }
        return false;
    }
}
