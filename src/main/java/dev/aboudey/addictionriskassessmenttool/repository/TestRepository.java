package dev.aboudey.addictionriskassessmenttool.repository;

import dev.aboudey.addictionriskassessmenttool.mode.*;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestRepository {

    private final Path filePath;

    public TestRepository(Path filePath) {
        this.filePath = filePath;
    }

    public void save(Test test) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath.toFile()))) {

            writer.write("TEST|" + test.getId() + "|" + test.getTitle());
            writer.newLine();

            for (Question question : test.getQuestions()) {
                writer.write("QUESTION|" + question.getId() + "|" + question.getText());
                writer.newLine();

                for (Answer answer : question.getAnswers()) {
                    writer.write(
                            "ANSWER|" +
                                    answer.getId() + "|" +
                                    answer.getText() + "|" +
                                    answer.getRiskLevel()
                    );
                    writer.newLine();
                }
            }
            writer.write("END");
        }
    }

    public Test load() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(filePath.toFile()))) {

            String line;
            Test test = null;
            Question currentQuestion = null;
            List<Question> questions = new ArrayList<>();

            while ((line = reader.readLine()) != null) {

                if (line.equals("END")) break;

                String[] parts = line.split("\\|");

                switch (parts[0]) {

                    case "TEST" -> {
                        test = new Test(
                                Integer.parseInt(parts[1]),
                                parts[2],
                                new ArrayList<>()
                        );
                    }

                    case "QUESTION" -> {
                        currentQuestion = new Question(
                                Integer.parseInt(parts[1]),
                                parts[2]
                        );
                        questions.add(currentQuestion);
                    }

                    case "ANSWER" -> {
                        if (currentQuestion == null) {
                            throw new IllegalStateException("ANSWER without QUESTION");
                        }

                        Answer answer = new Answer(
                                Integer.parseInt(parts[1]), // Id
                                parts[2],                   //txt
                                Integer.parseInt(parts[3])  // risk lvl
                        );
                        currentQuestion.addAnswer(answer);
                    }
                }
            }

            if (test != null) {
                test.getQuestions().addAll(questions);
            }

            return test;
        }
    }
}
