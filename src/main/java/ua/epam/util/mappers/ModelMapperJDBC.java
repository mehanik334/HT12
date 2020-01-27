package ua.epam.util.mappers;

import ua.epam.model.Account;
import ua.epam.model.AccountStatus;
import ua.epam.model.Developer;
import ua.epam.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelMapperJDBC {

    public static ArrayList<Account> getAccountListFromMap(ResultSet resultSet) throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            Account account = new Account();
            String accountStatusStr = resultSet.getString("status");
            AccountStatus accountStatus = AccountStatus.valueOf(accountStatusStr);
            Long id = resultSet.getLong("id");
            account.setId(id);
            account.setAccountStatus(accountStatus);
            accounts.add(account);

        }
        return accounts;
    }

    public static ArrayList<Skill> getSkillListFromMap(ResultSet resultSet) throws SQLException {
        ArrayList<Skill> skills = new ArrayList<>();
        while (resultSet.next()) {
            Skill skill = new Skill();
            String skillName = resultSet.getString("name");
            Long id = resultSet.getLong("id");
            skill.setId(id);
            skill.setName(skillName);
            skills.add(skill);
        }
        return skills;
    }

    public static ArrayList<Developer> mapToDeveloper(ResultSet resultSet) throws SQLException {
        ArrayList<Developer> developers = new ArrayList<>();
        while (resultSet.next()) {
            long accountId = resultSet.getInt("a.id");
            long skillId = resultSet.getInt("s.id");
            long developerId = resultSet.getInt("d.id");

            String skillName = resultSet.getString("name");
            String accountStatus = resultSet.getString("status");
            String firstNameDeveloper = resultSet.getString("firstName");
            String lastNameDeveloper = resultSet.getString("lastName");

            Developer developer = new Developer();
            Account account = new Account();
            Skill skill = new Skill();

            skill.setId(skillId);
            skill.setName(skillName);

            if (developers.size() > 0) {
                if (developerId == developers.get(developers.size() - 1).getId()) {
                    developers.get(developers.size() - 1).setSkill(skill);
                    continue;
                }
            }
            account.setId(accountId);
            account.setAccountStatus(AccountStatus.valueOf(accountStatus));
            developer.setId(developerId);
            developer.setFirstName(firstNameDeveloper);
            developer.setLastName(lastNameDeveloper);
            developer.setAccount(account);
            developer.setSkill(skill);
            developers.add(developer);
        }
        return developers;
    }
}
