package dev.aboudey.addictionriskassessmenttool.ui;

import dev.aboudey.addictionriskassessmenttool.builder.TestBuilder;
import dev.aboudey.addictionriskassessmenttool.mode.Answer;
import dev.aboudey.addictionriskassessmenttool.mode.Question;
import dev.aboudey.addictionriskassessmenttool.mode.Test;
import dev.aboudey.addictionriskassessmenttool.repository.TestRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Path;
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

        Test test = new TestBuilder()
                .setTitle(titleField.getText())
                .build();

        List<TextField> questionFields = getAllTextFields(questionContainer, "questionField");
        List<TextField> answerFields = getAllTextFields(answersContainer, "answerField");

        for (int q = 0; q < questionFields.size(); q++) {
            String qText = questionFields.get(q).getText();

            System.out.println(questionFields);// ***طابعة الفيلد** (object)
            System.out.println("Question #" + q + ": '" + qText + "'");  // **طباعة نص السؤال**
            Question question = new Question(q, qText);

            for (int a = 0; a < answerFields.size(); a++) {
                Answer answer = new Answer(a, answerFields.get(a).getText(), 5);
                question.addAnswer(answer);
            }
            test.addQuestion(question);
        }

        System.out.println("Test saved: " + test.getTitle());

        try {
            Path path = Path.of("src/main/resources/tests/" + test.getTitle()  + ".txt");
            TestRepository repo = new TestRepository(path);
            repo.save(test);
        } catch (IOException e) {
            System.out.print("Failed to save test");
            e.printStackTrace();
        }
    }


    @FXML
    public void onAddQuestionClick() throws IOException{
        // add question vboox
        VBox newQuestionOption = FXMLLoader.load(getClass().getResource("/shortcuts/QuestionOption.fxml"));

        // get all childrn in vbox without adding the vbox
       questionContainer.getChildren().add(newQuestionOption);

    }


    // get all text fields in VBox ( answer container ) to add it to question

    private List<TextField> getAllTextFields(Parent parent, String styleClass) { // the vbox parent
        List<TextField> result = new ArrayList<>();

        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof TextField tf && tf.getStyleClass().contains(styleClass)) {
                result.add(tf);
            } else if (node instanceof Parent p) {
                result.addAll(getAllTextFields(p, styleClass));
            }
        }
        return result;
    }
   /* private Question getQuestionLoops(Test test) {
        List<TextField> fields = getAllTextFields(questionContainer, "questionField");
       for (int i = 0; i<= fields.size() - 1; i++) {
            Question question = new Question(i, fields.get(i).getText());
            test.addQuestion(question);
            System.out.printf("added question: " + fields.get(i).getText() + " to test");
       return question;
        }
       return null;
    }

    // get the answer for current test and current question
    private Answer getAnswerLoops(Test test) {
        List<TextField> fields = getAllTextFields(answersContainer, "answerField");
        for (int i = 0; i<= fields.size() - 1; i++) {
            Answer answer = new Answer(i, fields.get(i).getText(), 5);
            System.out.printf("added answer:" + fields.get(i).getText() + " to question");
            return answer;
        }
        return null;
    }
*/

}
