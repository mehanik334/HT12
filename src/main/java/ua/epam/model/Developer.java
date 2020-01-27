package ua.epam.model;

import java.util.ArrayList;
import java.util.Objects;

public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private ArrayList<Skill> skills = new ArrayList<>();
    private Account account;

    public Developer() {
    }

    public Developer(String firstName, String lastName, Skill skill, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills.add(skill);
        this.account = account;
    }

    public Developer(Long id, String firstName, String lastName, ArrayList<Skill> skills, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setSkill(Skill skill) {
        this.skills.add(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id) &&
                Objects.equals(firstName, developer.firstName) &&
                Objects.equals(lastName, developer.lastName) &&
                Objects.equals(skills, developer.skills) &&
                Objects.equals(account, developer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, skills, account);
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", skills=" + skills +
                ", account=" + account +
                '}';
    }
}
