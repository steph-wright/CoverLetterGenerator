package org.example.service;

import org.example.entity.Skill;
import org.example.persistence.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component("service")
public class ServiceImpl implements org.example.service.Service {

    private Dao dao;

    private String[] hardSkillIntros = {"I possess expertise in ", "I am passionate about ",
            "I can offer skills in ", "I am proficient in ",
            "I have a strong background in ", "Throughout my career I have acquired a very good knowledge of ",
            "I am adept at ", "I have a proven record of excelling at "};


    @Autowired
    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public HashMap<String, Skill> getAllSkills() {
        return dao.getAllSkills();
    }

    @Override
    public Collection<String> processJobDescription(String jobDesc) {

        return List.of(jobDesc.split(" "));
    }

    @Override
    public HashMap<String, Skill> getSomeSkills(Collection<String> jobDescriptionSkills) {

        return dao.getSomeSkills(jobDescriptionSkills);
    }

    @Override
    public String generateCoverLetter(HashMap<String, Skill> relevantSkills) {
        String coverLetter = new String();
        int i = 0;

        for (Skill skill: relevantSkills.values()) {

            if (i > hardSkillIntros.length)
                i = 0;

            coverLetter += hardSkillIntros[i] + skill.getSkillName() +
                    ", for example, " + skill.getSkillExplanation() + " ";

            i++;
        }

        return coverLetter;
    }

    @Override
    public boolean addSkill(Skill skill) {
        if (skill.getSkillName() == null || skill.getSkillExplanation() == null)
            return false;
        else {
            dao.addSkill(skill);
            return true;
        }
    }

    @Override
    public boolean deleteSkill(String name) {
        if (dao.getSkill(name) == null)
            return false;
        else
            dao.deleteSkill(name);
        return true;
    }

    @Override
    public boolean updateSkill(Skill skill) {
        if (dao.getSkill(skill.getSkillName()) == null)
            return false;
        else
            dao.addSkill(skill);
            return true;
    }
}
