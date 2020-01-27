package ua.epam.repositories.io;


import ua.epam.model.Skill;
import ua.epam.repositories.SkillRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SkillIORepositoryImpl implements SkillRepository {

    public static final String FILE_PATH = "skills.txt";
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;

    public SkillIORepositoryImpl() {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
            this.fileWriter = new FileWriter(FILE_PATH, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill getById(Long id) {
        try {
            String skillStr = bufferedReader.readLine();
            while (skillStr != null) {
                Skill skill = transformStringToSkill(skillStr);
                if (id.equals(skill.getId())) {
                    return skill;
                }
                skillStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Skill> getAll() {
        ArrayList<Skill> skillList = new ArrayList<>();
        try {
            String skill = bufferedReader.readLine();
            while (skill != null) {
                skillList.add(transformStringToSkill(skill));
                skill = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    public static Skill transformStringToSkill(String skillStr) {
        String[] arrStr = skillStr.split("-");
        return new Skill(Long.parseLong(arrStr[0]), arrStr[1]);
    }

    @Override
    public Skill save(Skill skill) {
        if (!hasSkill(skill)) {
            try {

                fileWriter.append('\n');
                fileWriter.write(skill.toString());
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return skill;
    }

    @Override
    public Skill deleteById(Long id) {
        try {
            String skillStr = bufferedReader.readLine();
            ArrayList<Skill> skillList = new ArrayList<>();
            Skill skill = null;
            while (skillStr != null) {
                skill = transformStringToSkill(skillStr);
                skillList.add(skill);
                if (id.equals(skill.getId())) {
                    skillList.remove(skill);
                }
                skillStr = bufferedReader.readLine();
            }
            saveSkillsListToFile(skillList);
            return skill;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Skill skill) {
        try {
            String skillStr = bufferedReader.readLine();
            while (skillStr != null) {
                Skill skillFromFile = transformStringToSkill(skillStr);
                if (skill.getId().equals(skillFromFile.getId())) {
                    fileWriter.write(skill.toString());
                    fileWriter.append('\n');
                    fileWriter.flush();
                    return true;
                }
                skillStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveSkillsListToFile(ArrayList<Skill> skills) {
        for (Skill s : skills) {
            save(s);
        }
    }

    public boolean hasSkill(Skill skill) {
        ArrayList<Skill> allSkill = getAll();
        for (Skill s : allSkill) {
            if (s.getName().equals(skill.getName())) {
                return true;
            }
        }
        return false;
    }


}
