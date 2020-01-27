package ua.epam.view;


import ua.epam.controller.AccountController;
import ua.epam.model.Account;

import java.util.Scanner;

public class AccountView {


    private AccountController accountController;

    public AccountView() {
        this.accountController = new AccountController();
    }


    public Account inputAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set account status :");
        System.out.println("1 - Active");
        System.out.println("2 - Banned");
        System.out.println("3 - Deleted");
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong input");
            scanner.next();
        }
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                return accountController.getByIdAccount(1L);
            case 2:
                return accountController.getByIdAccount(2L);
            case 3:
                return accountController.getByIdAccount(3L);
        }
        return null;
    }
}
