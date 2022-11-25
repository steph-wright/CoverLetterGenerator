package org.example.persistence;

import org.example.entity.Skill;

import java.util.Collection;
import java.util.HashMap;

public interface Dao {

    // FETCH FULL SKILL TABLE
    public HashMap<String, Skill> getAllSkills();

    // RETURN SKILL TABLE BASED ON SEARCH CRITERIA
    public HashMap <String, Skill> getSomeSkills(Collection<String> jobDescription);

    // GET SKILL
    public Skill getSkill(String name);

    // ADD SKILL
    public Skill addSkill(Skill skill);

    // REMOVE SKILL
    public Skill deleteSkill(String name);

    // UPDATE SKILL


}
