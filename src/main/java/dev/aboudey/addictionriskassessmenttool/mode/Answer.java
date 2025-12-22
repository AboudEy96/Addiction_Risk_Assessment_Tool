package dev.aboudey.addictionriskassessmenttool.mode;

import java.util.List;

public class Answer {

    private int id;
    private String text;
    private int riskLevel;

    public Answer(int id, String text, int riskLevel) {
        this.id = id;
        this.riskLevel = riskLevel;
        this.text = text;

    }

    public String getText() {return text;} public int getId() {return id;}
    public int getRiskLevel() {
        return riskLevel;
    }
}
