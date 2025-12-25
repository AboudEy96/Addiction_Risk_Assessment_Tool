package dev.aboudey.addictionriskassessmenttool.ui;

import dev.aboudey.addictionriskassessmenttool.mode.Question;
import dev.aboudey.addictionriskassessmenttool.mode.Test;
import dev.aboudey.addictionriskassessmenttool.repository.ResultRepository;
import dev.aboudey.addictionriskassessmenttool.repository.Singletoon.SelectedTest;
import dev.aboudey.addictionriskassessmenttool.repository.TestRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestScreen
{
    @FXML
    private Label testTitleLabel;

    @FXML
    private Label questionTextLabel;

    // butons
    @FXML
    private Button nextButton;

    @FXML
    private RadioButton answerYes;


    public List<Test> tests;

    private int currentQuestion = 0;
    private int riskLevel;
    @FXML
    private VBox answersContainer;

    @FXML
    public void initialize() {
        System.out.println("initialize CALLED");
        updateFields();
    }
    // list of questions

    // st
    private final List<Question> questionsList = SelectedTest.selectedQuestions;
    private final String textName = SelectedTest.selectedTestName;


    @FXML
    private void onNextButtonClick() throws InterruptedException, IOException {
        if (!isAnswerSelected()) return;

        addRiskForYes();

        if (currentQuestion < questionsList.size() - 1) {
            currentQuestion++;
            updateFields();
            answerYes.setSelected(false);
        } else {
            double avg = (double) riskLevel / questionsList.size() * 100;

          /*  ResultRepository.results.add(
                    new ResultRepository.Result(textName, avg)
            );*/
            ResultRepository.saveResult(textName, avg);


            testTitleLabel.setText("you finished " + textName);
            questionTextLabel.setText("Your avg risk is " + (int) avg + "%");
            answersContainer.setVisible(false);

            Parent root = FXMLLoader.load(
                    getClass().getResource("/dev/aboudey/addictionriskassessmenttool/hello-view.fxml")
            );
            Stage stage = (Stage) answersContainer
                    .getScene()
                    .getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }



    private void updateFields() {
        System.out.println("updateFields CALLED");
        testTitleLabel.setText(SelectedTest.selectedTestName);
        questionTextLabel.setText(SelectedTest.selectedQuestions.get(currentQuestion).getText());

    }
    private boolean isAnswerSelected() {
        for (Node b : answersContainer.getChildren()) {
            if (b instanceof RadioButton) {
                RadioButton rb = (RadioButton) b;
                if (rb.isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addRiskForYes() {
        if (answerYes.isSelected()) {
            riskLevel++;
        }
    }



}
