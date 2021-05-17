package persistence;

import model.Instructor;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ReaderCatalogue {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public ReaderCatalogue(String source) {
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

    // EFFECTS: Parses a studentList from Catalogue JSONObject and returns a list of the containing teams
    public ArrayList<Student> readStudents() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray studentArray = jsonObject.getJSONArray("studentList");
        ArrayList<Student> students = new ArrayList<>();
        for (Object s: studentArray) {
            JSONObject studentObject = (JSONObject) s;
            String name = studentObject.getString("name");
            String email = studentObject.getString("email");
            String id = studentObject.getString("id");
            String pass = studentObject.getString("pass");
            students.add(new Student(name,email,id,pass));
        }
        return students;

    }

    // EFFECTS: Parses a instructorList from Catalogue JSONObject and returns a list of the containing teams
    public ArrayList<Instructor> readInstructors() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray instructorArray = jsonObject.getJSONArray("instructorList");
        ArrayList<Instructor> instructors = new ArrayList<>();
        for (Object i: instructorArray) {
            JSONObject instructorObject = (JSONObject) i;
            String name = instructorObject.getString("name");
            String email = instructorObject.getString("email");
            String id = instructorObject.getString("id");
            String pass = instructorObject.getString("pass");
            instructors.add(new Instructor(name,email,id,pass));
        }
        return instructors;

    }
}
