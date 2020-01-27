package ua.epam.model;

import java.util.Objects;

public class Account {

    private Long id;
    private AccountStatus accountStatus;

    public Account() {
    }

    public Account(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Account(Long id, AccountStatus accountStatus) {
        this.id = id;
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                accountStatus == account.accountStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountStatus);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
