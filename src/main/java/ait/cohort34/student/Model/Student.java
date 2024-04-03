package ait.cohort34.student.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private String password;
    private Map<String, Integer> scores;

    public Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        scores = new HashMap<>();
    }
    public boolean addScore(String exam, int score) {
        return scores.put(exam, score) == null;
    }
}

