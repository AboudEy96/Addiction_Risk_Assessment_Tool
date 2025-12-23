package dev.aboudey.addictionriskassessmenttool.ui;

import dev.aboudey.addictionriskassessmenttool.mode.Test;
import dev.aboudey.addictionriskassessmenttool.repository.TestRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestScreen
{
    @FXML
    private TextField testTitle;

    @FXML
    private TextField questionField;

    // butons
    @FXML
    private TextField nextButton;

    public List<Test> tests;

    @FXML
    public void initialize() {
        System.out.println("initialize CALLED");

    }
    // list of questions

    @FXML
    public void onNextButtonClick() {
        //load next question from test list .
    }


}
