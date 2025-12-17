package dev.aboudey.addictionriskassessmenttool.builder;

import dev.aboudey.addictionriskassessmenttool.mode.Question;
import dev.aboudey.addictionriskassessmenttool.mode.Test;

import java.util.ArrayList;
import java.util.List;

public class TestBuilder implements Builder
{
    private int id;
    private String title;
    private List<Question> questions = new ArrayList<>();


    @Override
    public TestBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public TestBuilder setID(int ID) {
        this.id = ID;
        return this;
    }

    @Override
    public TestBuilder addQuestions(Question questions) {
        this.questions.add(questions);
        return this;
    }

    @Override
    public TestBuilder setType(String type) {
        type = "Selection";
        return this;
    }

    public Test build() {
        return new Test(id,title, questions);
    }
}
