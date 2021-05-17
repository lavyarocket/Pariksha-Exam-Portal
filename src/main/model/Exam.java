package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

//Exam class holds a list of Questions which make up an exam
public class Exam implements Writable {
    //ArrayList of questions in a particular exam
    public List<Question> questionList = new ArrayList<>();
    //Object of class question used to add questions in questionList
    protected Question question;
    //Title for Exam
    String title;
    //Name of Instructor who made the exam
    String name;

    //Parametrized Constructor
    public Exam(List<Question> questionList, String name, String title) {
        this.questionList = questionList;
        this.name = name;
        this.title = title;
    }

    //Default Constructor
    public Exam() {
    }

    //MODIFIES: this
    //EFFECTS: Adds news questions to an Exam
    public void addQuestion(String ques, String answer) {
        question = new Question(ques, answer);
        questionList.add(question);
    }

    //MODIFIES: this
    //EFFECTS: Adds the title and instructor name to an exam
    public void addTitleAndName(String title, String name) {
        this.title = title;
        this.name = name;
    }

    //EFFECTS: Methods used to display various exams
    @Override
    public String toString() {
        return " Exam: " + this.title + " by Instructor: " + this.name;
    }

    //EFFECTS: Converts this class into a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("title",title);
        JSONArray jsonArray = new JSONArray();
        for (Question question : questionList) {
            jsonArray.put(question.toJson());
        }
        json.put("questionList",jsonArray);
        return json;
    }
}
