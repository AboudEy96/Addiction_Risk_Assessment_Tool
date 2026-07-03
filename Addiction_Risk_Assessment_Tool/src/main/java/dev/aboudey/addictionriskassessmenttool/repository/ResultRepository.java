package dev.aboudey.addictionriskassessmenttool.repository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ResultRepository {

    public static class Result {
        public String testName;
        public double risk;

        public Result(String testName, double risk) {
            this.testName = testName;
            this.risk = risk;
        }
    }

    private static final String FILE_PATH = "results.txt";

    public static List<Result> results = new ArrayList<>();

    public static void saveResult(String testName, double risk) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(testName + "|" + risk);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadResults() {
        results.clear();

        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    results.add(
                            new Result(parts[0], Double.parseDouble(parts[1]))
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
