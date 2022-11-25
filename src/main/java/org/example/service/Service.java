package org.example.service;

import org.example.entity.Skill;

import java.util.Collection;
import java.util.HashMap;

public interface Service {

    // FETCH FULL SKILL TABLE
    public HashMap<String, Skill> getAllSkills();

    // PROCESS JOB DESC INTO LIST OF STRINGS
    public Collection<String> processJobDescription(String jobDesc);


    public String generateCoverLetter(HashMap<String, Skill> relevantSkills);

    HashMap<String, Skill> getSomeSkills(Collection<String> jobDescriptionSkills);

    // ADD SKILL
    boolean addSkill(Skill skill);

    // REMOVE SKILL
    boolean deleteSkill(String name);

    // UPDATE SKILL
    boolean updateSkill(Skill skill);
}
