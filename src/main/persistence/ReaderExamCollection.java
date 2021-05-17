package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ReaderExamCollection {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public ReaderExamCollection(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads an ExamCollection JSON file, and return the containing item
    public ExamCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExamCollection(jsonObject);
    }

    // EFFECTS: returns a ExamCollection from a read JSONObject
    private ExamCollection parseExamCollection(JSONObject jsonObject) {
        ExamCollection examCollection = new ExamCollection();
        examCollection.copyExamList(readExamList(jsonObject));
        return examCollection;
    }

    // EFFECTS: Parses an ExamList JSONObject and returns a list of the containing teams
    public ArrayList<Exam> readExamList(JSONObject jsonObject) {
        JSONArray examArray = jsonObject.getJSONArray("examList");
        ArrayList<Exam> exams = new ArrayList<>();
        for (Object e: examArray) {
            JSONObject examObject = (JSONObject) e;
            String name = examObject.getString("name");
            String title = examObject.getString("title");
            exams.add(new Exam(readQuestionList(examObject),name,title));
        }
        return exams;

    }

    // EFFECTS: Parses an QuestionList JSONObject and returns a list of the containing teams
    public ArrayList<Question> readQuestionList(JSONObject jsonObject) {
        JSONArray questionArray = jsonObject.getJSONArray("questionList");
        ArrayList<Question> questions = new ArrayList<>();
        for (Object q: questionArray) {
            JSONObject questionObject = (JSONObject) q;
            String ques = questionObject.getString("ques");
            String answer = questionObject.getString("answer");
            questions.add(new Question(ques,answer));
        }
        return questions;

    }
}
