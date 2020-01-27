package ua.epam.view;

import ua.epam.controller.SkillController;
import ua.epam.model.Skill;

import java.util.ArrayList;
import java.util.Scanner;

public class SkillView {


    private SkillController skillController;

    public SkillView() {
        this.skillController = new SkillController();
    }

    public Skill getChoiceFromView() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Skill> skills = skillController.getAllSkill();
        System.out.println("Select a skill from the list below, enter the corresponding number");
        showSkillsFromFile(skills);
        System.out.println(" 0 -Add new skill");
        while (true) {
            int choiceNumber = scanner.nextInt();
            if (skillController.validateChoiceUser(choiceNumber, skills)) {
                if (choiceNumber == 0) {
                    System.out.println("Enter new skill");
                    Scanner scanner1 = new Scanner(System.in);
                    String newStrSkill = scanner1.nextLine();
                    Skill newSkill = new Skill(newStrSkill);
                    Skill skillSaveInFile = skillController.saveSkill(newSkill);
                    System.out.println("Add is complete");
                    System.out.println(skillSaveInFile);
                    return skillSaveInFile;
                } else {
                    return skills.get(choiceNumber - 1);
                }
            } else {
                System.out.println("Wrong enter, try again");
            }
        }
    }

    private void showSkillsFromFile(ArrayList<Skill> skillArrayList) {
        for (Skill s : skillArrayList) {
            System.out.println(s);
        }
    }
}
