package dev.aboudey.addictionriskassessmenttool.mode;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private int id;
    private String title;
    private List<Question> questions;


    public Test(int id, String title, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public void addQuestion(Question newQues) {
        questions.add(newQues);
    }

    public double calculateRisk() {
        double sum = 0;
        for(Question avg : questions) {
            sum+= avg.calculateRisk();
        }

    return sum/questions.size();
    }

    public String getTitle() {
        return title;
    }
    public int getId () {return id;}
    public List<Question> getQuestions() { return questions;}
}
