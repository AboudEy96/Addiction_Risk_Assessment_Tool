package dev.aboudey.addictionriskassessmenttool.builder;

import dev.aboudey.addictionriskassessmenttool.mode.Question;
import dev.aboudey.addictionriskassessmenttool.mode.Test;

import java.util.List;

public interface Builder
{

    Builder setTitle(String name);
    Builder setID(int ID);
    Builder addQuestions(Question questions);
    Builder setType(String type);
    Test build();
    //    Test test = new TestBuilder().setTitle("name").setId(id).addAuestions(questions).setType(selection).build();
}
