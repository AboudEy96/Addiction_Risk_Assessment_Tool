package dev.aboudey.addictionriskassessmenttool.ui;

import dev.aboudey.addictionriskassessmenttool.builder.TestBuilder;
import dev.aboudey.addictionriskassessmenttool.mode.Answer;
import dev.aboudey.addictionriskassessmenttool.mode.Question;
import dev.aboudey.addictionriskassessmenttool.mode.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateTestScreen {


    // title in create-test scene to make new test
    @FXML
    private TextField titleField;

   // butons
    @FXML
    private Button saveButton;
    @FXML
    private Button questionAddButton;



    // answer containers for Answers and for Questions to make prototype and copy paste it
    @FXML
    private VBox answersContainer;
    @FXML
    private VBox questionContainer;


    // ======= events =======

    @FXML
    private void onAddAnswerButtonClick() throws IOException {
        HBox newAnswerOption = FXMLLoader.load(getClass().getResource("/shortcuts/AnswerOption.fxml"));

        answersContainer.getChildren().add(newAnswerOption);
    }

    // make the test
    @FXML
    public void onSaveButtonClick() {
        // get all text fields in one question and add it
        List<TextField> fields = getAllTextFields(answersContainer);

        Test test = new TestBuilder().setTitle(titleField.getText()).build();
        getAnswerLoops(test, new Question(1, "hiii it's test"));
        System.out.printf(test.getTitle());

    }

    @FXML
    public void onAddQuestionClick() throws IOException{
        // add question vboox
        VBox newQuestionOption = FXMLLoader.load(getClass().getResource("/shortcuts/QuestionOption.fxml"));

        // get all childrn in vbox without adding the vbox
       questionContainer.getChildren().add(newQuestionOption);

    }


    // get all text fields in VBox ( answer container ) to add it to question

    private List<TextField> getAllTextFields(Parent parent) { // the vbox parent
        List<TextField> result = new ArrayList<>();

        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof TextField tf) {
                result.add(tf);
            } else if (node instanceof Parent p) {
                result.addAll(getAllTextFields(p));
            }
        }
        return result;
    }

    // get the answer for current test and current question
    private void getAnswerLoops(Test test, Question question) {
        List<TextField> fields = getAllTextFields(answersContainer);
        for (int i = 0; i<= fields.size() - 1; i++) {
            Answer answer = new Answer(i, fields.get(i).getText(), 5);
            question.addAnswer(answer);
            System.out.printf("added answer:" + fields.get(i).getText() + " to question");
        }
        test.addQuestion(question);
    }


}
