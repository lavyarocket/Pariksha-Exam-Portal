package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//ExamCollection class stores a list of exams created by various Instructors
public class ExamCollection extends Catalogue implements Writable {
    //ArrayList for Exams which stores multiple exams
    public static List<Exam> examList = new ArrayList<>();
    //Static object for exam used by sub classes
    public static Exam exam = null;

    //MODIFIES: this
    //EFFECTS: Adds a new exam into the Exam List
    public void addExam(Exam exam) {
        examList.add(exam);
    }

    //MODIFIES: this
    //EFFECTS: Copies the examList locally
    public void copyExamList(List<Exam> e) {
        examList = e;
    }

    //EFFECTS: Converts this class into a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Exam exam : examList) {
            jsonArray.put(exam.toJson());
        }
        json.put("examList",jsonArray);
        return json;
    }
}
