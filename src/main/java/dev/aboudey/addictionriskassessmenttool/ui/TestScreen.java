package dev.aboudey.addictionriskassessmenttool.ui;

import dev.aboudey.addictionriskassessmenttool.mode.Test;
import dev.aboudey.addictionriskassessmenttool.repository.Singletoon.SelectedTest;
import dev.aboudey.addictionriskassessmenttool.repository.TestRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public List<Test> tests;

    private int currentQuestion = 0;

    @FXML
    public void initialize() {
        System.out.println("initialize CALLED");
        updateFields();
    }
    // list of questions

    @FXML
    private void onNextButtonClick() {
         currentQuestion++;
         updateFields();
    }

    private void updateFields() {
        System.out.println("updateFields CALLED");
        testTitleLabel.setText(SelectedTest.selectedTestName);
        questionTextLabel.setText(SelectedTest.selectedQuestions.get(currentQuestion).getText());

    }


}
