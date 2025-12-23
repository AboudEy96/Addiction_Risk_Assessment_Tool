package dev.aboudey.addictionriskassessmenttool.mode;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private String text;
    private List<Answer> answers;
    // user selected answer
    public Answer selectedAns;

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
    public void setSelectedAns(Answer selected) {
        selectedAns = selected;
    }

    public int getId() {return id;}
    public List<Answer> getAnswers (){return answers;}
    public String getText() {return text;}
    public int calculateRisk() {
        if (selectedAns == null) return 0;

        return selectedAns.getRiskLevel();
    }
}
