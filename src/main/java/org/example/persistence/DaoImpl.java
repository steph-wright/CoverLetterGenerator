package org.example.persistence;

import org.example.entity.Skill;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

@Component("dao")
public class DaoImpl implements Dao {
    public static final String DELIMITER = "::";
    public static final String DATA = "skilldata.txt";

    private HashMap<String, Skill> skillsList = new HashMap<>();


    @Override
    public HashMap<String, Skill> getAllSkills() {

        readData();
        return skillsList;

    }

    @Override
    public Skill getSkill(String name) {

        readData();

        return skillsList.get(name);

    }

    @Override
    public HashMap<String, Skill> getSomeSkills(Collection<String> jobDescription) {
        readData();

        HashMap<String, Skill> someSkills = new HashMap<>();

        for (String word: jobDescription) {
            for (String skill: skillsList.keySet()) {
                if (StringUtils.containsIgnoreCase(word, skill)) {
                    someSkills.put(skill, skillsList.get(skill));
                }

            }
        }

        return someSkills;
    }

    @Override
    public Skill addSkill(Skill skill) {

        readData();

        Skill oldSkill = skillsList.put(skill.getSkillName(), skill);

        writeData();

        return oldSkill;
    }

    @Override
    public Skill deleteSkill(String name) {

        readData();

        Skill oldSkill = skillsList.remove(name);

        writeData();

        return oldSkill;
    }



    public String marshalSkill(Skill skill) {
        String name = skill.getSkillName();
        String explanation = skill.getSkillExplanation();

        String marshalledSkill = name + DELIMITER + explanation;
        return marshalledSkill;
    }

    public Skill unmarshallSkill(String line) {
        String[] tokens = line.split(DELIMITER);
        String name = tokens[0];
        String explanation = tokens[1];

        return new Skill(name, explanation);
    }

    public void readData() {

        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(DATA)));

            String currentLine;
            Skill currentSkill;

            while(scanner.hasNext()) {
                currentLine = scanner.nextLine();
                currentSkill = unmarshallSkill(currentLine);
                skillsList.put(currentSkill.getSkillName(),currentSkill);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void writeData() {

        try {
            PrintWriter out = new PrintWriter(new FileWriter(DATA));

            String marshalledSkill;

            for (Skill skill: skillsList.values()) {
                marshalledSkill = marshalSkill(skill);
                out.println(marshalledSkill);
            }

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
