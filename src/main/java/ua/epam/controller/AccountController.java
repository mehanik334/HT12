package ua.epam.controller;


import ua.epam.model.Account;
import ua.epam.repositories.AccountRepository;
import ua.epam.repositories.jdbc.AccountJDBCRepositoryImpl;

import java.util.ArrayList;

public class AccountController {

    private AccountRepository accountRepository;

    public AccountController() {
        this.accountRepository = new AccountJDBCRepositoryImpl();
    }

    public Account getByIdAccount(Long id) {
        return accountRepository.getById(id);
    }

    public ArrayList<Account> getAllAccount() {
        return accountRepository.getAll();
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account deleteByIdAccount(Long id) {
        return accountRepository.deleteById(id);
    }

    public boolean updateAccount(Account account) {
        return accountRepository.update(account);
    }
}
