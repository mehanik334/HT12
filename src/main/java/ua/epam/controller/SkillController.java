package ua.epam.controller;


import ua.epam.model.Skill;
import ua.epam.repositories.SkillRepository;
import ua.epam.repositories.jdbc.SkillJDBCRepositoryImpl;

import java.util.ArrayList;

public class SkillController {

    private SkillRepository skillRepository;

    public SkillController() {
        this.skillRepository = new SkillJDBCRepositoryImpl();
    }


    public boolean validateChoiceUser(int number, ArrayList<Skill> skills) {
        if (number <= skills.size() + 1 && number >= 0) {
            return true;
        }
        return false;
    }


    public ArrayList<Skill> getAllSkill() {
        return skillRepository.getAll();
    }

    public Skill getByIdSkill(Long id) {
        return skillRepository.getById(id);
    }

    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill deleteSkillById(Long id) {
        return skillRepository.deleteById(id);
    }

    public boolean updateSkill(Skill skill) {
        return skillRepository.update(skill);
    }
}
