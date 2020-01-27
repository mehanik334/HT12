package ua.epam.view;


import ua.epam.controller.DeveloperController;
import ua.epam.model.Account;
import ua.epam.model.Developer;
import ua.epam.model.Skill;

import java.util.ArrayList;
import java.util.Scanner;

public class DeveloperView {

    private DeveloperController developerController;
    private SkillView skillView;
    private AccountView accountView;

    public DeveloperView() {
        this.developerController = new DeveloperController();
        this.skillView = new SkillView();
        this.accountView = new AccountView();
    }

    public void getChoiceFromView() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make a choice from the list what is possible to do");
        System.out.println("1 - get developer by id in DB;");
        System.out.println("2 - get all developers from DB;");
        System.out.println("3 - save new developer;");
        System.out.println("4 - delete developer by id in DB");
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter id ");
                    if (scanner.hasNext()) {
                        System.out.println(developerController.getByIdDeveloper(scanner.nextLong()));
                    } else {
                        System.out.println("No match");
                    }
                    break;
                case 2:
                    ArrayList<Developer> result = developerController.getAllDevelopers();
                    for (Developer d : result) {
                        System.out.println(d);
                    }
                    break;
                case 3:
                    System.out.println("Enter first name and then through a space last name :");
                    Scanner in = new Scanner(System.in);
                    String firstLastName = in.nextLine();
                    String [] names = firstLastName.split(" ");
                    Skill skill = skillView.getChoiceFromView();
                    Account account = accountView.inputAccount();
                    Developer developer = new Developer(names[0], names[1],skill, account);
                    Developer saveDeveloper = developerController.saveDeveloper(developer);
                    System.out.println("Save " + saveDeveloper);
                    break;

                case 4:
                    System.out.println("Enter id ");
                    if (scanner.hasNext()) {
                        System.out.println(developerController.deleteByIdDeveloper(scanner.nextLong()));
                    } else {
                        System.out.println("No match");
                    }
                    break;
            }
        }
    }
}
