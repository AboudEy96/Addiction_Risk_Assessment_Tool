package dev.aboudey.addictionriskassessmenttool;

import dev.aboudey.addictionriskassessmenttool.mode.Answer;
import dev.aboudey.addictionriskassessmenttool.mode.Question;
import dev.aboudey.addictionriskassessmenttool.repository.Singletoon.SelectedTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MainController {

    @FXML
    private Label welcomeText;

    @FXML
    private VBox testsBoxHandler;

    @FXML
    public void initialize() {
        System.out.println("initialize CALLED");

        try {
            URL url = getClass().getResource("/tests");
            if (url == null) {
                System.out.println("tests folder NOT FOUND");
                return;
            }

            Path testsPath = Paths.get(url.toURI());

            Files.list(testsPath)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(this::createTestButton);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCreateTestButtonClick(ActionEvent e) throws IOException {
        Parent createSC = FXMLLoader.load(
                getClass().getResource("create-test.fxml")
        );

        Stage stage = (Stage) ((Node) e.getSource())
                .getScene().getWindow();

        stage.setScene(new Scene(createSC));
        stage.show();
    }

    private void createTestButton(Path testFile) {
        System.out.println("Creating button for: " + testFile.getFileName());

        String testName = testFile.getFileName()
                .toString()
                .replace(".txt", "");

        Button btn = new Button(testName);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        btn.setStyle("""
    -fx-background-color: #0EA5E9;
    -fx-text-fill: white;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-background-radius: 10;
    -fx-cursor: hand;
""");
        btn.getStyleClass().add("testButton");
        //
        btn.setOnAction(e -> openTest(testFile));

        testsBoxHandler.getChildren().add(btn);
    }

    private void openTest(Path testFile) {
        System.out.println("Opening test file: " + testFile.getFileName());

        try {
            List<String> lines = Files.readAllLines(testFile);

            Question currentQuestion = null;

            for (String line : lines) {

                String[] parts = line.split("\\|");

                switch (parts[0]) {

                    case "TEST" -> {
                        SelectedTest.selectedTestName = parts[2];
                    }

                    case "QUESTION" -> {
                        int id = Integer.parseInt(parts[1]);
                        String text = parts[2];

                        currentQuestion = new Question(id, text);
                        SelectedTest.selectedQuestions.add(currentQuestion);
                    }

                    case "ANSWER" -> {
                        if (currentQuestion == null) break;

                        int id = Integer.parseInt(parts[1]);
                        String text = parts[2];
                        int score = Integer.parseInt(parts[3]);

                        currentQuestion.addAnswer(
                                new Answer(id, text, score)
                        );
                    }

                    case "END" -> {
                        System.out.println("Test loaded successfully");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
