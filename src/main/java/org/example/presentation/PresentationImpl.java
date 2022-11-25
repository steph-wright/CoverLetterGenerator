package org.example.presentation;

import org.example.entity.Skill;
import org.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

@Component("presentation")
public class PresentationImpl implements Presentation {

    private Service service;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public PresentationImpl(Service service) {
        this.service = service;
    }

    @Override
    public void displayMenu() {
        System.out.println("=========================");
        System.out.println(" COVER LETTER GENERATOR");
        System.out.println("=========================");
        System.out.println("1. Display all skills");
        System.out.println("2. Generate Cover Letter");
        System.out.println("3. Add skill");
        System.out.println("4. Remove skill");
        System.out.println("5. Update skill");
        System.out.println("6. Exit");
        System.out.println("=========================");
    }

    @Override
    public int getChoice() {
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }

    @Override
    public void executeMenu(int x) {
        switch (x) {
            case 1:
                displayAllSkills();
                break;
            case 2:
                displayGenerate();
                break;
            case 3:
                displayAddSkill();
                break;
            case 4:
                displayRemoveSkill();
                break;
            case 5:
                displayUpdateSkill();
                break;
            case 6:
                System.out.println("Thank you for using Cover Letter Generator.");
                System.exit(0);
            default:
                System.out.println("Invalid input. Try again.");
                break;
        }
    }


    public void displayAllSkills() {
        HashMap<String, Skill> skills = service.getAllSkills();
        for (Skill skill: skills.values()) {
            System.out.println(skill.getSkillName() + " || " + skill.getSkillExplanation());
        }
    }

    public void displayAddSkill() {
        System.out.println("Enter name of skill: ");
        String skillName = scanner.nextLine();
        System.out.println("Enter one or two sentences evidencing this skill, on one line, ending in a full stop: ");
        String skillExplanation = scanner.nextLine();
        Skill skill = new Skill(skillName, skillExplanation);
        boolean result = service.addSkill(skill);
        if (result)
            System.out.println("Skill successfully added to database.");
        else
            System.out.println("There is already a listed skill with that name. Either update this skill use another name.");
    }

    public void displayRemoveSkill() {
        System.out.println("Enter name of skill to be removed: ");
        String skillName = scanner.nextLine();
        boolean wasDeleted = service.deleteSkill(skillName);
        if (wasDeleted)
            System.out.println("Skill successfully removed.");
        else
            System.out.println("Skill was not found in the database, so can't be removed.");
    }

    public void displayUpdateSkill() {
        System.out.println("Enter name of skill to update: ");
        String skillName = scanner.nextLine();
        System.out.println("Provide updated explanation, ending in a full stop: ");
        String skillExplanation = scanner.nextLine();
        boolean wasUpdated = service.updateSkill(new Skill(skillName, skillExplanation));
        if (wasUpdated)
            System.out.println("Skill successfully updated");
        else
            System.out.println("Skill was not found in the database, so can't be updated.");
    }

    public void displayGenerate() {
        System.out.println("Please copy & paste your person specification here, then press enter: ");
        String personSpec = scanner.nextLine();
        Collection<String> personSpecWords = service.processJobDescription(personSpec);
        HashMap<String, Skill> relevantSkills = service.getSomeSkills(personSpecWords);
        String coverLetter = service.generateCoverLetter(relevantSkills);
        System.out.println(coverLetter);
    }

}
