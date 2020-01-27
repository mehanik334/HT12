package ua.epam.controller;


import ua.epam.model.Developer;
import ua.epam.repositories.DeveloperRepository;
import ua.epam.repositories.jdbc.DeveloperJDBCRepositoryImpl;

import java.util.ArrayList;

public class DeveloperController {

    private DeveloperRepository developerRepository;

    public DeveloperController() {
        this.developerRepository = new DeveloperJDBCRepositoryImpl();
    }

    public Developer getByIdDeveloper(Long id) {
        return developerRepository.getById(id);
    }

    public ArrayList<Developer> getAllDevelopers() {
        return developerRepository.getAll();
    }

    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public Developer deleteByIdDeveloper(Long id) {
        return developerRepository.deleteById(id);
    }

    public boolean updateDeveloper(Developer developer) {
        return developerRepository.update(developer);
    }

}
