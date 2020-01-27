package ua.epam.repositories.io;


import ua.epam.model.Developer;
import ua.epam.model.Skill;
import ua.epam.repositories.DeveloperRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DeveloperIORepositoryImpl implements DeveloperRepository {

    public static final String FILE_PATH = "developer.txt";
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private AccountIORepositoryImpl accountRepository;


    public DeveloperIORepositoryImpl() {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
            this.fileWriter = new FileWriter(FILE_PATH, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Long id) {
        try {
            String developerStr = bufferedReader.readLine();
            while (developerStr != null) {
                Developer developer = transformStringToDeveloper(developerStr);
                if (id.equals(developer.getId())) {
                    return developer;
                }
                developerStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Developer> getAll() {
        ArrayList<Developer> developerList = new ArrayList<>();
        try {
            String developerStr = bufferedReader.readLine();
            while (developerStr != null) {
                developerList.add(transformStringToDeveloper(developerStr));
                developerStr = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developerList;
    }

    @Override
    public Developer save(Developer developer) {
        if (!hasDeveloper(developer)) {
            try {

                fileWriter.append('\n');
                fileWriter.write(developer.toString());
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return developer;
    }

    @Override
    public Developer deleteById(Long id) {
        try {
            String developerStr = bufferedReader.readLine();
            ArrayList<Developer> developerList = new ArrayList<>();
            Developer developer = null;
            while (developerStr != null) {
                developer = transformStringToDeveloper(developerStr);
                developerList.add(developer);
                if (id.equals(developer.getId())) {
                    developerList.remove(developer);
                }
                developerStr = bufferedReader.readLine();
            }
            saveDevelopersListToFile(developerList);
            return developer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Developer obj) {
        try {
            String developerStr = bufferedReader.readLine();
            while (developerStr != null) {
                Developer developerFromFile = transformStringToDeveloper(developerStr);
                if (obj.equals(developerFromFile)) {
                    fileWriter.write(obj.toString());
                    fileWriter.append('\n');
                    fileWriter.flush();
                    return true;
                }
                developerStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Developer transformStringToDeveloper(String developerStr) {
        String[] arrStr = developerStr.split("-");
        String[] skills = arrStr[3].split(",");
        ArrayList<Skill> skillList = new ArrayList<>();
        for (int i = 0; i < skills.length; i++) {
            skillList.add(SkillIORepositoryImpl.transformStringToSkill(skills[i]));
        }
        return new Developer(Long.parseLong(arrStr[0]), arrStr[1], arrStr[2], skillList, AccountIORepositoryImpl.transformStringToAccount(arrStr[4]));
    }

    private boolean hasDeveloper(Developer developer) {
        ArrayList<Developer> allDevelopers = getAll();
        for (Developer d : allDevelopers) {
            if (d.equals(developer)) {
                return true;
            }
        }
        return false;
    }

    private void saveDevelopersListToFile(ArrayList<Developer> developers) {
        for (Developer d : developers) {
            save(d);
        }
    }
}
