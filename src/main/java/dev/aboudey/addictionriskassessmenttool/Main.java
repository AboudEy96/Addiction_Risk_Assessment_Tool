package dev.aboudey.addictionriskassessmenttool;

import dev.aboudey.addictionriskassessmenttool.builder.TestBuilder;
import dev.aboudey.addictionriskassessmenttool.mode.Answer;
import dev.aboudey.addictionriskassessmenttool.mode.Question;
import dev.aboudey.addictionriskassessmenttool.mode.Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Question question = new Question(1, "Do you use cigarettes");
        Answer yes = new Answer(1, "Yes", 5);
        Answer no = new Answer(2, "No", 3);

        question.setSelectedAns(yes);

        Question question1 = new Question(2, "Do you use alcohol ?");
        question1.setSelectedAns(no);

        Test test = new TestBuilder().setID(1)
                .setTitle("Main")
                .setType("Selection").
                addQuestions(question).addQuestions(question1).build();


        System.out.printf("test" + test.calculateRisk());


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}